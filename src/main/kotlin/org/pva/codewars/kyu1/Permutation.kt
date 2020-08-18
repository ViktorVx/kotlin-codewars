package org.pva.codewars.kyu1

import java.util.*

class Permutation {

    companion object {

        @JvmStatic fun show(mines:Int, cells:Int) {
            val arr = arrayOf("A", "B", "C", "D", "E", "F")
            combinations(arr, 3, 0, arrayOfNulls<String>(3))
        }

        fun combinations(arr: Array<String>, len: Int, startPosition: Int, result: Array<String?>) {
            if (len == 0) {
                System.out.println(Arrays.toString(result))
                return
            }
            for (i in startPosition..arr.size - len) {
                result[result.size - len] = arr[i]
                combinations(arr, len - 1, i + 1, result)
            }
        }
    }
}