package org.pva.KotlinCodewars.Kyu2


fun parseRegexp(s: String):String {
    println(s)
    var ss = s.replace(Regex("\\*"), "*N")
    val ltrL = listOf('a', 'b', '.')
    val ltrR = listOf('a', 'b', '.', '(')
    var ssRes = ""
    for ((i, s) in ss.toCharArray().iterator().withIndex()) {
        ssRes += if (i == ss.length - 1) s else if (s in ltrL && ss[i + 1] in ltrR) "$s+" else s
    }
    return parse(ssRes)
}

fun parse(s: String):String {
    if (s.length == 1) return if (s == ".") "Any()" else "Normal ('$s')"
    val ltr = listOf('a', 'b', '.', 'N')
    val map = mapOf('*' to 1, '+' to 2, '|' to 3)
    var ind = 0
    var w = 0
    var brk = 0
    for ((i, c) in s.toCharArray().iterator().withIndex()) {
        if (c in ltr) continue
        if (c == '(') {
            brk++
            continue
        }
        if (c == ')') {
            brk--
            continue
        }
        if (brk != 0) continue
        if (w <= map[c] ?: error("No such operation")) {
            w = map[c] ?: error("No such operation")
            ind = i
        }
    }
    if (ind == 0) return parse(s.substring(1, s.length - 1))

    val oper = s[ind]
    val left = s.substring(0, ind)
    val right = s.substring(ind + 1, s.length)
    return when (oper) {
        '*' -> "ZeroOrMore (${parse(left)})"
        '|' -> "Or (${parse(left)}, ${parse(right)})"
        else -> "Str ([${parse(left)}, ${parse(right)}])"
    }
}
