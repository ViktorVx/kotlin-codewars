package org.pva.KotlinCodewars.kyu7

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Kyu7 {

    private fun testing(actual:Int, expected:Int) {
        assertEquals(expected.toLong(), actual.toLong())
    }
    @Test
    fun test1() {
        println("Fixed Tests: nbYear")
        testing(nbYear(1500, 5.0, 100, 5000), 15)
        testing(nbYear(1500000, 2.5, 10000, 2000000), 10)

    }

}