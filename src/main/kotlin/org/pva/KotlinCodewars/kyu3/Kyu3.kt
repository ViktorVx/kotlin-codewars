package org.pva.KotlinCodewars.kyu3

import java.util.*


fun parseRegexp(s: String):String {
    val q = Stack<Char>()
    val w = Stack<Char>()
    val ltr = listOf('a', 'b', '.')
    val sgn = listOf('|', '*', '(', ')')

    for (c in s.toCharArray()) {
        if (c in ltr) q.push(c) else w.push(c)
    }
    println(q)
    println(w)
//    parseStacks(q, w)
    //------
//    if (s.length == 1) return "Normal(\'$s\')"
//    if (!s.contains(Regex("\\.|\\*|\\|"))) {
//        var ins = s.replace(Regex("\\(|\\)"), "")
//        ins = ins.toCharArray().map { "Normal(\'$it\')" }.joinToString(", ")
//        return "Str ([$ins])"
//    }
    return parseStacks(q, w)
}

fun parseStacks(q: Stack<Char>, w: Stack<Char>):String {
    if (w.pop() == '*') {
        return "ZeroOrMore (${parseStacks(q, w)})"
    }
    return ""
}

