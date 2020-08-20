package org.pva.codewars.kyu1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MineSweeperTest {

    private val data = arrayOf(// Sample Tests:
            arrayOf("Simple map 1",
                    """1 x 1 1 x 1
                      |2 2 2 1 2 2
                      |2 x 2 0 1 x
                      |2 x 2 1 2 2
                      |1 1 1 1 x 1
                      |0 0 0 1 1 1""".trimMargin(),
                    """? ? ? ? ? ?
                      |? ? ? ? ? ?
                      |? ? ? 0 ? ?
                      |? ? ? ? ? ?
                      |? ? ? ? ? ?
                      |0 0 0 ? ? ?""".trimMargin(),
                    """1 x 1 1 x 1
                      |2 2 2 1 2 2
                      |2 x 2 0 1 x
                      |2 x 2 1 2 2
                      |1 1 1 1 x 1
                      |0 0 0 1 1 1""".trimMargin()),

            arrayOf("Simple map 2",
                    """0 2 x
                      |0 2 x""".trimMargin(),
                    """0 ? ?
                      |0 ? ?""".trimMargin(),
                    """0 2 x
                      |0 2 x""".trimMargin()),

            arrayOf("Simple unsolvable map",
                    """0 1 x
                      |0 1 1""".trimMargin(),
                    """0 ? ?
                      |0 ? ?""".trimMargin(),
                    "?"),

            arrayOf("Simple map 3",
                    """1 x x 1 0 0 0
                      |2 3 3 1 0 1 1
                      |1 x 1 0 0 1 x
                      |1 1 1 0 0 1 1
                      |0 1 1 1 0 0 0
                      |0 1 x 1 0 0 0
                      |0 1 1 1 0 1 1
                      |0 0 0 0 0 1 x
                      |0 0 0 0 0 1 1""".trimMargin(),
                    """? ? ? ? 0 0 0
                      |? ? ? ? 0 ? ?
                      |? ? ? 0 0 ? ?
                      |? ? ? 0 0 ? ?
                      |0 ? ? ? 0 0 0
                      |0 ? ? ? 0 0 0
                      |0 ? ? ? 0 ? ?
                      |0 0 0 0 0 ? ?
                      |0 0 0 0 0 ? ?""".trimMargin(),
                    """1 x x 1 0 0 0
                      |2 3 3 1 0 1 1
                      |1 x 1 0 0 1 x
                      |1 1 1 0 0 1 1
                      |0 1 1 1 0 0 0
                      |0 1 x 1 0 0 0
                      |0 1 1 1 0 1 1
                      |0 0 0 0 0 1 x
                      |0 0 0 0 0 1 1""".trimMargin()),

            arrayOf("Various unsolvable map - 1",
                    """1 1 0 1 1 1 0 0 1 1 1 0 0 0 0 1 1 1 0
                      |x 1 0 1 x 1 0 0 2 x 2 0 0 0 0 1 x 2 1
                      |1 1 0 2 3 3 1 1 3 x 2 0 0 0 0 1 2 x 1
                      |0 1 1 2 x x 1 2 x 3 1 0 0 0 0 0 1 1 1
                      |0 1 x 2 2 2 1 3 x 3 0 0 0 0 0 0 0 0 0
                      |0 1 1 1 0 0 0 2 x 2 0 0 0 0 0 0 0 0 0
                      |0 0 0 0 0 0 0 1 1 1 1 2 2 1 0 0 0 0 0
                      |0 0 0 0 0 0 0 0 0 0 1 x x 1 0 0 0 0 0
                      |0 0 1 1 1 0 1 1 1 0 1 2 2 1 0 0 0 0 0
                      |0 0 1 x 2 1 3 x 2 0 0 0 0 0 0 1 1 1 0
                      |0 0 1 1 2 x 3 x 3 1 1 0 0 0 0 1 x 1 0
                      |0 0 0 0 1 2 3 2 2 x 1 0 0 0 0 1 1 1 0
                      |0 0 0 0 0 1 x 1 1 1 1 0 0 0 0 0 1 1 1
                      |0 0 1 1 2 2 2 1 0 0 0 0 0 0 0 0 1 x 1
                      |0 0 1 x 2 x 2 1 1 0 0 0 0 0 0 0 1 1 1
                      |0 0 1 1 2 1 3 x 3 1 0 0 0 0 0 0 0 1 1
                      |0 0 0 0 0 0 2 x x 1 0 0 0 1 1 1 0 1 x
                      |0 0 0 1 1 1 1 2 2 1 0 0 0 1 x 1 1 2 2
                      |0 0 0 1 x 3 2 1 0 0 0 1 1 2 1 1 1 x 2
                      |0 0 0 1 2 x x 1 0 0 0 1 x 1 0 1 2 3 x
                      |0 0 0 0 1 2 2 1 1 1 1 1 1 1 0 1 x 3 2
                      |0 0 0 0 1 1 1 1 2 x 1 1 1 1 0 2 3 x 2
                      |0 0 0 0 1 x 1 1 x 2 1 1 x 1 0 1 x 3 x""".trimMargin(),
                    """? ? 0 ? ? ? 0 0 ? ? ? 0 0 0 0 ? ? ? 0
                      |? ? 0 ? ? ? 0 0 ? ? ? 0 0 0 0 ? ? ? ?
                      |? ? 0 ? ? ? ? ? ? ? ? 0 0 0 0 ? ? ? ?
                      |0 ? ? ? ? ? ? ? ? ? ? 0 0 0 0 0 ? ? ?
                      |0 ? ? ? ? ? ? ? ? ? 0 0 0 0 0 0 0 0 0
                      |0 ? ? ? 0 0 0 ? ? ? 0 0 0 0 0 0 0 0 0
                      |0 0 0 0 0 0 0 ? ? ? ? ? ? ? 0 0 0 0 0
                      |0 0 0 0 0 0 0 0 0 0 ? ? ? ? 0 0 0 0 0
                      |0 0 ? ? ? 0 ? ? ? 0 ? ? ? ? 0 0 0 0 0
                      |0 0 ? ? ? ? ? ? ? 0 0 0 0 0 0 ? ? ? 0
                      |0 0 ? ? ? ? ? ? ? ? ? 0 0 0 0 ? ? ? 0
                      |0 0 0 0 ? ? ? ? ? ? ? 0 0 0 0 ? ? ? 0
                      |0 0 0 0 0 ? ? ? ? ? ? 0 0 0 0 0 ? ? ?
                      |0 0 ? ? ? ? ? ? 0 0 0 0 0 0 0 0 ? ? ?
                      |0 0 ? ? ? ? ? ? ? 0 0 0 0 0 0 0 ? ? ?
                      |0 0 ? ? ? ? ? ? ? ? 0 0 0 0 0 0 0 ? ?
                      |0 0 0 0 0 0 ? ? ? ? 0 0 0 ? ? ? 0 ? ?
                      |0 0 0 ? ? ? ? ? ? ? 0 0 0 ? ? ? ? ? ?
                      |0 0 0 ? ? ? ? ? 0 0 0 ? ? ? ? ? ? ? ?
                      |0 0 0 ? ? ? ? ? 0 0 0 ? ? ? 0 ? ? ? ?
                      |0 0 0 0 ? ? ? ? ? ? ? ? ? ? 0 ? ? ? ?
                      |0 0 0 0 ? ? ? ? ? ? ? ? ? ? 0 ? ? ? ?
                      |0 0 0 0 ? ? ? ? ? ? ? ? ? ? 0 ? ? ? ?""".trimMargin(),
                    "?"),

            arrayOf("Various unsolvable map - 2",
                    """0 0 0 0 0 0 0 0 1 x x 2 1 0 1 x 1 0 1 2 x
                      |0 0 0 0 0 0 0 0 1 2 3 x 1 0 2 2 2 1 2 x 2
                      |0 0 0 0 0 0 0 0 0 0 2 2 2 0 1 x 1 1 x 2 1
                      |0 0 0 0 0 1 1 1 0 0 1 x 1 0 1 2 2 2 1 1 0
                      |1 1 0 0 0 1 x 1 0 1 2 2 1 0 0 1 x 1 1 1 1
                      |x 1 0 0 0 1 1 1 0 1 x 1 0 0 0 1 1 1 1 x 1
                      |2 2 1 0 0 0 0 0 0 1 1 1 0 0 0 0 0 0 1 1 1
                      |1 x 1 0 0 0 0 0 0 0 1 2 2 1 0 0 1 1 1 0 0
                      |1 1 1 0 0 0 0 0 0 0 1 x x 1 0 0 1 x 1 0 0""".trimMargin(),
                    """0 0 0 0 0 0 0 0 ? ? ? ? ? 0 ? ? ? 0 ? ? ?
                      |0 0 0 0 0 0 0 0 ? ? ? ? ? 0 ? ? ? ? ? ? ?
                      |0 0 0 0 0 0 0 0 0 0 ? ? ? 0 ? ? ? ? ? ? ?
                      |0 0 0 0 0 ? ? ? 0 0 ? ? ? 0 ? ? ? ? ? ? 0
                      |? ? 0 0 0 ? ? ? 0 ? ? ? ? 0 0 ? ? ? ? ? ?
                      |? ? 0 0 0 ? ? ? 0 ? ? ? 0 0 0 ? ? ? ? ? ?
                      |? ? ? 0 0 0 0 0 0 ? ? ? 0 0 0 0 0 0 ? ? ?
                      |? ? ? 0 0 0 0 0 0 0 ? ? ? ? 0 0 ? ? ? 0 0
                      |? ? ? 0 0 0 0 0 0 0 ? ? ? ? 0 0 ? ? ? 0 0""".trimMargin(),
                    "?")
    )

    private fun makeAssertionAndDisplay(message: String, expected: String, actual: String) {
        if (expected != actual) {
            println("""${if (message.isEmpty()) "Failed test!!" else message}
                |Expected:
                |$expected
                |
                |But was: 
                |$actual""".trimMargin())
        }
        assertEquals(expected, actual, message)
    }


    @Test
    fun sampleTests() {
        for (count in data.indices) {
            Game.newGame(data[count][1])
            makeAssertionAndDisplay(data[count][0], data[count][3], MineSweeper(data[count][2], Game.minesN).solve())
        }
    }
}