package org.pva.KotlinCodewars.kyu8

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Kyu8 {

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
        assertEquals(10, sum(listOf(5,"5")));
        assertEquals(22, sum(listOf(9, 3, "7", "3")));
        assertEquals(42, sum(listOf("5", "0", 9, 3, 2, 1, "9", 6, 7)));
        assertEquals(41, sum(listOf("3", 6, 6, 0, "5", 8, 5, "6", 2, "0")));
        assertEquals(45, sum(listOf("1", "5", "8", 8, 9, 9, 2, "3")));
        assertEquals(41, sum(listOf("3", 6, 6, 0, "5", 8, 5, "6", 2, "0")));
        assertEquals(61, sum(listOf(8, 0, 0, 8, 5, 7, 2, 3, 7, 8, 6, 7)));
    }

    @Test
    fun sample() {
        assertEquals("eating like I",reverseWords("I like eating") )
        assertEquals("flying like I", reverseWords("I like flying"))
        assertEquals("nice is world The", reverseWords("The world is nice"))
        assertEquals("it!! Split Just", reverseWords("Just Split it!!"))
        assertEquals("!!! !! !", reverseWords("! !! !!!"))
        assertEquals("7777 777 77 7", reverseWords("7 77 777 7777"))
        assertEquals("D : Jpazzy", reverseWords("Jpazzy : D"))
    }

    private fun printAndAssert(expected: Int, games: List<String>) {
        assertEquals(expected, points(games))
    }
}