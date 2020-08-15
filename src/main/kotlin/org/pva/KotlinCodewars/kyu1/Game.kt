package org.pva.KotlinCodewars.kyu1

class Game {

    companion object {
        val rfStr = """1 x 1 1 x 1
                  |2 2 2 1 2 2
                  |2 x 2 0 1 x
                  |2 x 2 1 2 2
                  |1 1 1 1 x 1
                  |0 0 0 1 1 1""".trimMargin()

        @JvmStatic public fun open(x: Int, y: Int): Int {
            val rfArr = vfStrToArr(rfStr)
            return Character.getNumericValue(rfArr[x][y])
        }

        private fun vfStrToArr(str:String): ArrayList<CharArray> {
            return str.lines()
                    .map { it.replace(Regex("\\s"), "").toCharArray() }
                    .toCollection(arrayListOf())
        }
    }
}