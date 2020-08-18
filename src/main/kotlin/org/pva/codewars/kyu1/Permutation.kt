package org.pva.codewars.kyu1

class Permutation {

    companion object {

        @JvmStatic fun show(mines:Int, cells:Int) {
            val arr = (0 until cells).toList().toIntArray()
            combinations(arr, mines, 0, arrayOfNulls<Int>(mines))
        }

        private fun combinations(arr: IntArray, len: Int, startPosition: Int, result: Array<Int?>) {
            if (len == 0) {
                println(result.contentToString())
                return
            }
            for (i in startPosition..arr.size - len) {
                result[result.size - len] = arr[i]
                combinations(arr, len - 1, i + 1, result)
            }
        }
    }
}