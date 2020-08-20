package org.pva.codewars.kyu1

import kotlin.Array as Array

class MineSweeper(board: String, nMines: Int) {

    private var vfStr = board
    private val nMns = nMines
    private var foundMines = 0

    fun solve(): String {
        val vfArr = vfStrToArr(vfStr)
        printVfArr(vfArr)
        //****************** Simple algorithm ******************
        while (true) {
            var step = simpleAlgorithm(vfArr, false)
            if (step == null) {
                if (checkClearField(vfArr)) break
                //****************** Probability algorithm ******************
                return bruteForceAlgorithm(vfArr)
                //***********************************************************
            } else {
                makeStep(step, vfArr,false)
            }
            printVfArr(vfArr)
        }
        return vfArrToStr(vfArr)
    }

    // todo реализовать алгоритм перебора возможных комбинаций размещения мин на поле (вместо вероятностного алгоритма)
    //  если оказывается, что вариант м.б. только один - то это и есть решение
    //  если вариантов размещения несколько, то поле неразрешимо
    //  Реализация:
    //  1) +++Собрать все неизвествные ячейки
    //  2) +++Создать список перестановок неизвестных ячеек по колучеству оставшихся мин
    //  3) +++Запустить обход составленных перестановок
    //      3.1) +++ Проставляем мины на координаты в соответствии с перестановками
    //      3.2) +++Если поле решается - запоминаем координаты проставленных мин
    //  4) Если существует больше 2х наборов решений - тогда поле неразрешимо, инача - решаем поле
    //  !!! Проверять на то, что хотя бы одна мина попадает на граничные ячейки с вероятностями

    private fun bruteForceAlgorithm(vfArr: ArrayList<CharArray>):String {
        println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
        val unknownCoords = mutableListOf<Pair<Int, Int>>()
        // Collect unknown cells
        for (indX in vfArr.indices) {
            for ((indY, y) in vfArr[indX].iterator().withIndex()) {
                if (y == '?') unknownCoords.add(Pair(indX, indY))
            }
        }
        // Collect probable combinations of mines dislocation
        val combinations = mutableListOf<IntArray>()
        combinations((0 until unknownCoords.size).toList().toIntArray(), nMns - foundMines, 0,
                IntArray(nMns - foundMines) { 0 }, combinations)
        val successCase = mutableListOf<IntArray>()
        for (re in combinations) {
            val caseField = mutableListOf<CharArray>()
            for (c in vfArr) {
                caseField.add(c.clone())
            }
            //******
            println(re.contentToString())
            for (i in re) {
                makeStep(Step(unknownCoords[i].first, unknownCoords[i].second, Flag.MINE), caseField, true)
            }
            //******
            while (true) {
                if (checkClearField(caseField) && checkValidField(caseField)) {
                    successCase.add(re)
                    if (successCase.size > 1) return "?"
                    break
                }

                var step = simpleAlgorithm(caseField, true)
                if (step == null || step.flag == Flag.MINE) {
                    break
                }
                makeStep(step, caseField, true)
            }
        }

        println(successCase)
        for (ints in successCase[0]) {
            makeStep(Step(unknownCoords[ints].first, unknownCoords[ints].second, Flag.MINE), vfArr, true)
        }
        //****************** Simple algorithm ******************
        while (true) {
            var step = simpleAlgorithm(vfArr, false)
            if (step == null) {
                if (checkClearField(vfArr)) break
            } else {
                makeStep(step, vfArr, true)
            }
        }
        return vfArrToStr(vfArr)
    }

    private fun combinations(arr: IntArray, len: Int, startPosition: Int, result: IntArray, res: MutableList<IntArray>) {
        if (len == 0) {
            res.add(result.clone())
            return
        }
        for (i in startPosition..arr.size - len) {
            result[result.size - len] = arr[i]
            combinations(arr, len - 1, i + 1, result, res)
        }
    }

    private fun vfStrToArr(str:String): ArrayList<CharArray> {
        return str.lines()
                .map { it.replace(Regex("\\s"), "").toCharArray() }
                .toCollection(arrayListOf())
    }

    private fun vfArrToStr(arr: MutableList<CharArray>): String {
        return arr.joinToString("\n") { it.joinToString(" ") }
    }

    private fun simpleAlgorithm(arr: MutableList<CharArray>, checkCase:Boolean): Step? {
        for (indX in arr.indices) {
            loop@ for ((indY, y) in arr[indX].iterator().withIndex()) {
                if (foundMines == nMns && !checkCase) {
                    return chooseAnyEmpty (indX, indY, arr, Flag.EMPTY) ?: continue
                }
                when(y) {
                    '?', 'x' -> continue@loop
                    else -> {
                        val cellValue = Character.getNumericValue(y)
                        val map = mutableMapOf("unknown" to 0, "mines" to 0)
                        if (indX != 0 && indY != 0) countSurrounding(arr[indX - 1][indY - 1], map)
                        if (indX != 0) countSurrounding(arr[indX - 1][indY], map)
                        if (indX != 0 && indY != arr[0].size - 1) countSurrounding(arr[indX - 1][indY + 1], map)
                        if (indY != arr[0].size - 1) countSurrounding(arr[indX][indY + 1], map)
                        if (indX != arr.size - 1 && indY != arr[0].size - 1) countSurrounding(arr[indX + 1][indY + 1], map)
                        if (indX != arr.size - 1) countSurrounding(arr[indX + 1][indY], map)
                        if (indX != arr.size - 1 && indY != 0) countSurrounding(arr[indX + 1][indY - 1], map)
                        if (indY != 0) countSurrounding(arr[indX][indY - 1], map)

                        if (cellValue < map["mines"]!!) return null
                        if (map["unknown"] == 0 && cellValue != map["mines"]) return null
                        if (map["unknown"] == 0) continue@loop
                        if (cellValue == map["mines"]) return chooseAnyEmpty(indX, indY, arr, Flag.EMPTY)
                        if (map["unknown"] == cellValue - map["mines"]!!) return chooseAnyEmpty(indX, indY, arr, Flag.MINE)
                    }
                }
            }
        }
        return null
    }

    private fun checkValidField(arr: MutableList<CharArray>): Boolean {
        for (indX in arr.indices) {
            loop@ for ((indY, y) in arr[indX].iterator().withIndex()) {
                when(y) {
                    '?', 'x' -> continue@loop
                    else -> {
                        val cellValue = Character.getNumericValue(y)
                        val map = mutableMapOf("unknown" to 0, "mines" to 0)
                        if (indX != 0 && indY != 0) countSurrounding(arr[indX - 1][indY - 1], map)
                        if (indX != 0) countSurrounding(arr[indX - 1][indY], map)
                        if (indX != 0 && indY != arr[0].size - 1) countSurrounding(arr[indX - 1][indY + 1], map)
                        if (indY != arr[0].size - 1) countSurrounding(arr[indX][indY + 1], map)
                        if (indX != arr.size - 1 && indY != arr[0].size - 1) countSurrounding(arr[indX + 1][indY + 1], map)
                        if (indX != arr.size - 1) countSurrounding(arr[indX + 1][indY], map)
                        if (indX != arr.size - 1 && indY != 0) countSurrounding(arr[indX + 1][indY - 1], map)
                        if (indY != 0) countSurrounding(arr[indX][indY - 1], map)

                        if (cellValue < map["mines"]!!) return false
                        if (map["unknown"] == 0 && cellValue != map["mines"]) return false
                        if (map["unknown"] == 0) continue@loop
                        if (cellValue == map["mines"]) return true
                        if (map["unknown"] == cellValue - map["mines"]!!) return true
                    }
                }
            }
        }
        return true
    }

    private fun chooseAnyEmpty(indX: Int, indY: Int, arr: MutableList<CharArray>, flag: Flag): Step? {
        if (indX != 0 && indY != 0 && arr[indX - 1][indY - 1] == '?') return Step(indX - 1, indY - 1, flag)
        if (indX != 0 && arr[indX - 1][indY] == '?') return Step(indX - 1, indY, flag)
        if (indX != 0 && indY != arr[0].size - 1 && arr[indX - 1][indY + 1] == '?') return Step(indX - 1, indY + 1, flag)
        if (indY != arr[0].size - 1 && arr[indX][indY + 1] == '?') return Step(indX, indY + 1, flag)
        if (indX != arr.size - 1 && indY != arr[0].size - 1 && arr[indX + 1][indY + 1] == '?') return Step(indX + 1, indY + 1, flag)
        if (indX != arr.size - 1 && arr[indX + 1][indY] == '?') return Step(indX + 1, indY, flag)
        if (indX != arr.size - 1 && indY != 0 && arr[indX + 1][indY - 1] == '?') return Step(indX + 1, indY - 1, flag)
        if (indY != 0 && arr[indX][indY - 1] == '?') return Step(indX, indY - 1, flag)
        return null
    }

    private fun makeStep(step: Step, visible: MutableList<CharArray>, checkCase: Boolean) {
        when(step.flag) {
            Flag.EMPTY -> {
                if (checkCase) {
                    val indX = step.x
                    val indY = step.y
                    val arr = visible
                    val map = mutableMapOf("unknown" to 0, "mines" to 0)
                    if (indX != 0 && indY != 0) countSurrounding(arr[indX - 1][indY - 1], map)
                    if (indX != 0) countSurrounding(arr[indX - 1][indY], map)
                    if (indX != 0 && indY != arr[0].size - 1) countSurrounding(arr[indX - 1][indY + 1], map)
                    if (indY != arr[0].size - 1) countSurrounding(arr[indX][indY + 1], map)
                    if (indX != arr.size - 1 && indY != arr[0].size - 1) countSurrounding(arr[indX + 1][indY + 1], map)
                    if (indX != arr.size - 1) countSurrounding(arr[indX + 1][indY], map)
                    if (indX != arr.size - 1 && indY != 0) countSurrounding(arr[indX + 1][indY - 1], map)
                    if (indY != 0) countSurrounding(arr[indX][indY - 1], map)

                    visible[step.x][step.y] = "${map["mines"]}".toCharArray()[0]
                } else {
                    visible[step.x][step.y] = "${Game.open(step.x, step.y)}".toCharArray()[0]
                }
            }
            Flag.MINE -> {
                visible[step.x][step.y] = 'x'
                foundMines++
            }
        }
    }

    private fun countSurrounding(arrVal:Char, map:MutableMap<String, Int>) {
        when (arrVal) {
            '?' -> map["unknown"]?.let { map.put("unknown", it +1) }
            'x' -> map["mines"]?.let { map.put("mines", it + 1) }
        }
    }

    private fun checkClearField(visible: MutableList<CharArray>): Boolean {
        for (indX in visible.indices) {
            for (y in visible[indX].iterator()) if (y == '?') return false
        }
        return true
    }

    private fun printVfArr(arr: MutableList<CharArray>) {
        println(vfArrToStr(arr))
        println("*".repeat(arr[0].size * 2 - 1))
    }

    class Step(val x: Int, val y:Int, val flag:Flag)

    enum class Flag {
        EMPTY, MINE
    }
}