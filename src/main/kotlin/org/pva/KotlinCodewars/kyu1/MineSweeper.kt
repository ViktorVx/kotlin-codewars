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
        return ""
    }

    fun vfStrToArr(str:String):Array<CharArray> {
        return emptyArray()
    }

    fun vfArrToStr(arr: Array<CharArray>): String {
        return ""
    }

}