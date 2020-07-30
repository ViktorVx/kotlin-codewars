package org.pva.KotlinCodewars.kyu8

fun points(games: List<String>) =
        games.sumBy {
            val (x, y) = it.split(":")
            when {
                x > y -> 3
                x < y -> 0
                else -> 1
            }
        }

fun digitize(n:Long):IntArray = n.toString().reversed().toCharArray().map { it.toString().toInt() }.toIntArray()

fun sum(mixed: List<Any>): Int =
        mixed.sumBy {
            when (it) {
                is String -> it.toInt()
                is Int -> it
                else -> 0
            }
        }

fun reverseWords(s: String): String = s.split(" ").reversed().joinToString(" ", "", "")

