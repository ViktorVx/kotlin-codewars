package org.pva.KotlinCodewars.kyu3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.pva.KotlinCodewars.Kyu2.RegExpParser

internal class Kyu2 {

    @Test
    fun testing() {
        assertEquals("Normal (\'a\')", RegExpParser("a").parse().toString())
        assertEquals("Str ([Normal (\'a\'), Normal (\'b\')])", RegExpParser("ab").parse().toString())
        assertEquals("Str ([Normal (\'a\'), Normal (\'b\')])", RegExpParser("(ab)").parse().toString())
        assertEquals("ZeroOrMore (Str ([Normal ('a'), Normal ('b')]))", RegExpParser("(ab)*").parse().toString())
        assertEquals("ZeroOrMore (Or (Normal ('a'), Normal ('b')))", RegExpParser("(a|b)*").parse().toString())
        assertEquals("Or (Str ([Normal ('a'), ZeroOrMore (Any())]), Str ([Normal ('b'), Normal ('b')]))", RegExpParser("(a.*)|(bb)").parse().toString())
        assertEquals("Or (Str ([Normal ('a'), Normal ('b')]), Normal ('a'))", RegExpParser("ab|a").parse().toString())
        assertEquals("Str ([Normal ('a'), Or (Normal ('b'), Normal ('a'))])", RegExpParser("a(b|a)").parse().toString())
        assertEquals("Or (Normal ('a'), ZeroOrMore (Normal ('b')))", RegExpParser("a|b*").parse().toString())
        assertEquals("Str ([Normal ('a'), ZeroOrMore (Any())])", RegExpParser("a.*").parse().toString())
    }
}