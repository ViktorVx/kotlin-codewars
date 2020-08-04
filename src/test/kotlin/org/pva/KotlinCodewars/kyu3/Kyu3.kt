package org.pva.KotlinCodewars.kyu3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Kyu3 {

    @Test
    fun testing() {
        assertEquals("Normal(\'a\')", parseRegexp("a"))
        assertEquals("Str ([Normal(\'a\'), Normal(\'b\')])", parseRegexp("ab"))
        assertEquals("Str ([Normal(\'a\'), Normal(\'b\')])", parseRegexp("(ab)"))
    }
}