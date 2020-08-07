package org.pva.KotlinCodewars.kyu1

class MineSweeper(val board: String, val nMines: Int) {

    val realField = """1 x 1 1 x 1
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

    fun solve(): String {
        val vfArr = vfStrToArr(vfStr)
        println(vfArrToStr(vfArr))
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

    fun printVfArr(arr: Array<CharArray>) {

    }

}