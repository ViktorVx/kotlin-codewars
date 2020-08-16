package org.pva.codewars.kyu1

import kotlin.Array as Array

class MineSweeper(board: String, nMines: Int) {

    private var vfStr = board
    private val nMns = nMines
    private var foundMines = 0

    fun solve(): String {
        val vfArr = vfStrToArr(vfStr)
        val probArr = Array(vfArr.size) { IntArray(vfArr[0].size) }
        printVfArr(vfArr)
        //****************** Simple algorithm ******************
        while (true) {
            var step = simpleAlgorithm(vfArr)
            if (step == null) {
                if (checkClearField(vfArr)) break
                //****************** Probability algorithm ******************
                probabilityAlgorithm(vfArr, probArr)
                step = analyseProbArray(probArr)
                if (step == null) return "?"
                //***********************************************************
                makeStep(step, vfArr)
            } else {
                makeStep(step, vfArr)
            }
            printVfArr(vfArr)
        }
        return vfArrToStr(vfArr)
    }

    // todo реализовать алгоритм перебора возможных комбинаций размещения мин на поле (вместо вероятностного алгоритма)
    //  если оказывается, что вариант м.б. только один - то это и есть решение
    //  если вариантов размещения несколько, то поле неразрешимо
    //  Реализация:
    //  1) Цикл по всем неизвестным ячейкам:
    //      1.1) Проставить мину в неизвестную ГРАНИЧНУЮ! ячейку
    //      1.2) Попробавть решить поле, если решение есть - запомнить координаты проставленных мин
    //  2) Если существует только один уникальный набор координат, то это и есть решение

    private fun analyseProbArray(probArr: Array<IntArray>): Step? {
        var maxVal = 0
        var maxX = 0
        var maxY = 0
        var average = 0
        var allHaveSameProbability = true
        for (indX in probArr.indices) {
            for ((indY, y) in probArr[indX].iterator().withIndex()) {
                if (y == 0) continue

                if (average == 0) average = y
                if (average != y) allHaveSameProbability = false

                if (maxVal < y) {
                    maxVal = y
                    maxX = indX
                    maxY = indY
                }
            }
        }
        return if (allHaveSameProbability) null else Step(maxX, maxY, Flag.MINE)

    }

    private fun vfStrToArr(str:String): ArrayList<CharArray> {
        return str.lines()
                .map { it.replace(Regex("\\s"), "").toCharArray() }
                .toCollection(arrayListOf())
    }

    private fun vfArrToStr(arr: ArrayList<CharArray>): String {
        return arr.joinToString("\n") { it.joinToString(" ") }
    }

    private fun simpleAlgorithm(arr: ArrayList<CharArray>): Step? {
        for (indX in arr.indices) {
            loop@ for ((indY, y) in arr[indX].iterator().withIndex()) {
                if (foundMines == nMns) {
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

                        if (map["unknown"] == 0) continue@loop
                        if (cellValue == map["mines"]) return chooseAnyEmpty(indX, indY, arr, Flag.EMPTY)
                        if (map["unknown"] == cellValue - map["mines"]!!) return chooseAnyEmpty(indX, indY, arr, Flag.MINE)
                    }
                }
            }
        }
        return null
    }

    private fun chooseAnyEmpty(indX: Int, indY: Int, arr: ArrayList<CharArray>, flag: Flag): Step? {
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

    private fun probabilityAlgorithm(arr: ArrayList<CharArray>, probArr: Array<IntArray>): Step? {
        for (indX in arr.indices) {
            loop@ for ((indY, y) in arr[indX].iterator().withIndex()) {
                if (foundMines == nMns) return chooseAnyEmpty(indX, indY, arr, Flag.EMPTY)
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

                        if (map["unknown"] == 0) continue@loop
                        val probability = 100 / map["unknown"]!! * (cellValue - map["mines"]!!)

                        if (indX != 0 && indY != 0 && arr[indX - 1][indY - 1] == '?') probArr[indX - 1][indY - 1]+=probability
                        if (indX != 0 && arr[indX - 1][indY] == '?') probArr[indX - 1][indY]+=probability
                        if (indX != 0 && indY != arr[0].size - 1 && arr[indX - 1][indY + 1] == '?') probArr[indX - 1][indY + 1]+=probability
                        if (indY != arr[0].size - 1 && arr[indX][indY + 1] == '?') probArr[indX][indY + 1]+=probability
                        if (indX != arr.size - 1 && indY != arr[0].size - 1 && arr[indX + 1][indY + 1] == '?') probArr[indX + 1][indY + 1]+=probability
                        if (indX != arr.size - 1 && arr[indX + 1][indY] == '?') probArr[indX + 1][indY]+=probability
                        if (indX != arr.size - 1 && indY != 0 && arr[indX + 1][indY - 1] == '?') probArr[indX + 1][indY - 1]+=probability
                        if (indY != 0 && arr[indX][indY - 1] == '?') probArr[indX][indY - 1]+=probability
                    }
                }
            }
        }
        return null
    }

    private fun makeStep(step: Step, visible: ArrayList<CharArray>) {
        when(step.flag) {
            Flag.EMPTY -> visible[step.x][step.y] = "${Game.open(step.x, step.y)}".toCharArray()[0]
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

    private fun checkClearField(visible: ArrayList<CharArray>): Boolean {
        for (indX in visible.indices) {
            for (y in visible[indX].iterator()) if (y == '?') return false
        }
        return true
    }

    private fun printVfArr(arr: ArrayList<CharArray>) {
        println(vfArrToStr(arr))
        println("*".repeat(arr[0].size * 2 - 1))
    }

    class Step(val x: Int, val y:Int, val flag:Flag)

    enum class Flag {
        EMPTY, MINE
    }
}