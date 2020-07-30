package org.pva.KotlinCodewars.kyu8

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class TotalAmountOfPointsTest {

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun pointsTest() {
        printAndAssert(30, listOf("1:0", "2:0", "3:0", "4:0", "2:1", "3:1", "4:1", "3:2", "4:2", "4:3"));
        printAndAssert(10, listOf("1:1", "2:2", "3:3", "4:4", "2:2", "3:3", "4:4", "3:3", "4:4", "4:4"));
        printAndAssert(0, listOf("0:1", "0:2", "0:3", "0:4", "1:2", "1:3", "1:4", "2:3", "2:4", "3:4"));
        printAndAssert(15, listOf("1:0", "2:0", "3:0", "4:0", "2:1", "1:3", "1:4", "2:3", "2:4", "3:4"));
        printAndAssert(12, listOf("1:0", "2:0", "3:0", "4:4", "2:2", "3:3", "1:4", "2:3", "2:4", "3:4"));
    }

    @Test
    fun tests() {
        assertArrayEquals(intArrayOf(1, 3, 2, 5, 3), digitize(35231))
    }

    private fun printAndAssert(expected: Int, games: List<String>) {
        println("Testing for games: $games")
        assertEquals(expected, points(games))
    }
}