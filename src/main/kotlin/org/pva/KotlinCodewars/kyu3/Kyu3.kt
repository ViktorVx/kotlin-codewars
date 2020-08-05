package org.pva.KotlinCodewars.kyu3


fun parseRegexp(s: String):String {
    var ss = s.replace(Regex("\\*"), "*N")
    val ltr = listOf('a', 'b', '.')
    var ssRes = ""
    for ((i, s) in ss.toCharArray().iterator().withIndex()) {
        if (s in ltr && ss[i + 1] in ltr) {
            ssRes += "$s+"
        } else {
            ssRes += s
        }
    }
    println(ssRes)
    return parse(ssRes)
}

fun parse(s: String):String {
    val ltr = listOf('a', 'b', '.')
    val map = mapOf('*' to 3, '+' to 2, '|' to 1)
    var ind = 0
    var w = 0
    var brk = 0
    for ((i, c) in s.toCharArray().iterator().withIndex()) {
        if (c == '(') {
            brk++
            continue
        }
        if (c == ')') {
            brk--
            continue
        }
        if (brk != 0) continue
        if (c in ltr) continue
        if (w <= map[c] ?: error("No such operation")) {
            w = map[c] ?: error("No such operation")
            ind = i
        }
    }
    if (ind == 0) {
        return parse(s.substring(1, s.length - 1))
    }
    val oper = s[ind]
    val left = s.substring(0, ind)
    val right = s.substring(ind + 1, s.length)
    println("oper: $oper, left: $left, right: $right")
    parse(left)
    parse(right)
    return ""
}

