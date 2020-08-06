package org.pva.KotlinCodewars.kyu3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Kyu3 {

    @Test
    fun testing() {
//        assertEquals("Normal(\'a\')", parseRegexp("a"))
//        assertEquals("Str ([Normal(\'a\'), Normal(\'b\')])", parseRegexp("ab"))
//        assertEquals("Str ([Normal(\'a\'), Normal(\'b\')])", parseRegexp("(ab)"))
//        assertEquals("ZeroOrMore (Or (Normal ('a'), Normal ('b')))", parseRegexp("(a|b)*"))
//        assertEquals("Or( Str([ Normal('a'), ZeroOrMore( Any() ) ]), Str([ Normal('b'), Normal('b') ]) )", parseRegexp("(a.*)|(bb)"))
        assertEquals("Or( Or( Str([Normal ('a'), ZeroOrMore( Any() )]), Str( [Normal('b'), Normal('b')] ) ), Str([ Normal('a'), Normal('b') ]) )", parseRegexp("(a.*)|(bb)|(ab)"))
    }
}