package org.pva.KotlinCodewars.kyu3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.pva.KotlinCodewars.Kyu2.parseRegexp

internal class Kyu2 {

    @Test
    fun testing() {
        assertEquals("Normal (\'a\')", parseRegexp("a"))
        assertEquals("Str ([Normal (\'a\'), Normal (\'b\')])", parseRegexp("ab"))
        assertEquals("Str ([Normal (\'a\'), Normal (\'b\')])", parseRegexp("(ab)"))
        assertEquals("ZeroOrMore (Str ([Normal ('a'), Normal ('b')]))", parseRegexp("(ab)*"))
        assertEquals("ZeroOrMore (Or (Normal ('a'), Normal ('b')))", parseRegexp("(a|b)*"))
        assertEquals("Or (Str ([Normal ('a'), ZeroOrMore (Any())]), Str ([Normal ('b'), Normal ('b')]))", parseRegexp("(a.*)|(bb)"))
        assertEquals("Or (Str ([Normal ('a'), Normal ('b')]), Normal ('a'))", parseRegexp("ab|a"))
        assertEquals("Str ([Normal ('a'), Or (Normal ('b'), Normal ('a'))])", parseRegexp("a(b|a)"))
        assertEquals("Or (Normal ('a'), ZeroOrMore (Normal ('b')))", parseRegexp("a|b*"))
        assertEquals("Str ([Normal ('a'), ZeroOrMore (Any())])", parseRegexp("a.*"))
    }
}