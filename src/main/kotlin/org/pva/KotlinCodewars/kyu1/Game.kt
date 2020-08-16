package org.pva.KotlinCodewars.kyu1

class Game {

    companion object {
        private var rfStr = ""
        var minesN = 0

        @JvmStatic fun open(x: Int, y: Int): Int {
            val rfArr = vfStrToArr(rfStr)
            return Character.getNumericValue(rfArr[x][y])
        }

        @JvmStatic fun newGame(arr:String) {
            rfStr = arr
            minesN = rfStr.toCharArray().filter { it == 'x' }.count()
        }

        private fun vfStrToArr(str:String): ArrayList<CharArray> {
            return str.lines()
                    .map { it.replace(Regex("\\s"), "").toCharArray() }
                    .toCollection(arrayListOf())
        }
    }
}