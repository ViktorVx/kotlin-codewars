package org.pva.KotlinCodewars.kyu7

fun nbYear(pp0:Int, percent:Double, aug:Int, p:Int):Int = nbcYear(pp0, percent, aug, p, 0)

fun nbcYear(pp0: Int, percent: Double, aug: Int, p: Int, y: Int): Int =
        if (pp0 >= p) y else nbcYear(pp0 + (pp0 * (percent / 100)).toInt() + aug, percent, aug, p, y + 1)

