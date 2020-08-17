package org.pva.codewars.kyu1

class Permutation {

    companion object {

        // todo реализовать алгоритм генерации сочетаний без повторений
        @JvmStatic fun show(mines:Int, cells:Int) {
            getNextNum(mines, cells)
        }

        private fun getNextNum(mines:Int, cells:Int) {
            val alphabet = (0 until cells).toList().toMutableList()
            println(alphabet)
            val array = Array(f(cells)/(f(cells - mines) * f(mines))) { IntArray(mines) {0} }

            for (i in 1 until mines) {
                array[0][i] = alphabet[i]
            }


            for ((i, c) in array.iterator().withIndex()) {
                if (i == 0) continue
                for (j in mines - 1 downTo 0) {
                    if (j == mines - 1) {
                        if (array[i - 1][j] == alphabet.max()) {
                            c[j - 1] = array[i - 1][j - 1] + 1
                            c[j] = c[j - 1] + 1
                        } else {
                            c[j] = array[i - 1][j] + 1
                        }
                    } else {
                        if (c[j] < array[i - 1][j]) c[j] = array[i - 1][j]
                    }
                }
            }
        }

        private fun f(n: Int): Int {
            var res = 1
            for (i in 1..n) res *= i
            return res
        }
    }
}