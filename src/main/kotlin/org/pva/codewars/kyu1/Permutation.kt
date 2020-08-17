package org.pva.codewars.kyu1

class Permutation {

    companion object {

        // todo реализовать алгоритм генерации сочетаний без повторений
        @JvmStatic fun show(mines:Int, cells:Int) {
//            val alphabet = (0 until cells).toList().toMutableList()
//            println(alphabet.subList(0, mines))
//            var i = mines - 1
//            while (true) {
//                if (alphabet[i] < cells - mines + i + 1) {
//                    alphabet[i] += 1
//                    for (j in i + 1 until mines) {
//                        alphabet[j] = alphabet[j-1] + 1
//                        println(alphabet.subList(0, mines))
//                        i = mines
//                    }
//                }
//                i--
//                if (i == -1) break
//            }
            val alphabet = (0 until cells).toList().toMutableList()
            var res = mutableListOf<IntArray>()

        }

        private fun getNextNum(alphabet:MutableList<>)
    }
}