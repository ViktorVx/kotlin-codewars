package org.pva.KotlinCodewars.kyu1

class MineSweeper(val board: String, val nMines: Int) {

    val rfStr = """1 x 1 1 x 1
                  |2 2 2 1 2 2
                  |2 x 2 0 1 x
                  |2 x 2 1 2 2
                  |1 1 1 1 x 1
                  |0 0 0 1 1 1""".trimMargin()
    var vfStr = """? ? ? ? ? ?
                  |? ? ? ? ? ?
                  |? ? ? 0 ? ?
                  |? ? ? ? ? ?
                  |? ? ? ? ? ?
                  |0 0 0 ? ? ?""".trimMargin()
    val nMns = 6
    var findedMines = 0

    fun solve(): String {
        var vfArr = vfStrToArr(vfStr)
        val rfArr = vfStrToArr(rfStr)
        printVfArr(vfArr)
        //******************
        for (c in 1..vfArr.size * vfArr[0].size) {
            var step = analyse(vfArr)
            if (step != null) {
                makeStep(step, vfArr, rfArr)
            }
            printVfArr(vfArr)
        }
        //******************
        return ""
    }

    private fun vfStrToArr(str:String): ArrayList<CharArray> {
        return str.lines()
                .map { it.replace(Regex("\\s"), "").toCharArray() }
                .toCollection(arrayListOf())
    }

    private fun vfArrToStr(arr: ArrayList<CharArray>): String {
        return arr.joinToString("\n") { it.joinToString(" ") }
    }

    private fun analyse(arr: ArrayList<CharArray>): Step? {
        for ((indX, x) in arr.iterator().withIndex()) {
            loop@ for ((indY, y) in arr[indX].iterator().withIndex()) {
                if (findedMines == nMns) return chooseAnyEmpty(indX, indY, arr, Flag.EMPTY)
                when(y) {
                    '?', 'x' -> continue@loop
                    else -> {
                        val cellValue = Character.getNumericValue(y)
                        var map = mutableMapOf("unknown" to 0, "mines" to 0)
                        if (indX != 0 && indY != 0) countSurrounding(arr[indX - 1][indY - 1], map)
                        if (indX != 0) countSurrounding(arr[indX - 1][indY], map)
                        if (indX != 0 && indY != arr[0].size - 1) countSurrounding(arr[indX - 1][indY + 1], map)
                        if (indY != arr[0].size - 1) countSurrounding(arr[indX][indY + 1], map)
                        if (indX != arr.size - 1 && indY != arr[0].size - 1) countSurrounding(arr[indX + 1][indY + 1], map)
                        if (indX != arr.size - 1) countSurrounding(arr[indX + 1][indY], map)
                        if (indX != arr.size - 1 && indY != 0) countSurrounding(arr[indX + 1][indY - 1], map)
                        if (indY != 0) countSurrounding(arr[indX][indY - 1], map)

                        if (map.get("unknown") == 0) continue@loop
                        if (cellValue == map.get("mines")) return chooseAnyEmpty(indX, indY, arr, Flag.EMPTY)
                        if (map.get("unknown") == cellValue - map.get("mines")!!) return chooseAnyEmpty(indX, indY, arr, Flag.MINE)
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

    private fun countSurrounding(arrVal:Char, map:MutableMap<String, Int>) {
        when (arrVal) {
            '?' -> map.get("unknown")?.let { map.put("unknown", it +1) }
            'x' -> map.get("mines")?.let { map.put("mines", it + 1) }
        }
    }

    private fun makeStep(step: Step, visible: ArrayList<CharArray>, real: ArrayList<CharArray>) {
        when(step.flag) {
            Flag.EMPTY -> if (real[step.x][step.y] != 'x') visible[step.x][step.y] = real[step.x][step.y] else throw WrongStepException()
            Flag.MINE -> if (real[step.x][step.y] == 'x') {
                visible[step.x][step.y] = real[step.x][step.y]
                findedMines++
            } else throw WrongStepException()
        }
    }

    private fun printVfArr(arr: ArrayList<CharArray>) {
        println(vfArrToStr(arr))
        println("*".repeat(arr[0].size * 2 - 1))
    }

    class Step(val x: Int, val y:Int, val flag:Flag)

    class WrongStepException: Exception()
    
    enum class Flag {
        EMPTY, MINE, QUESTION
    }
}