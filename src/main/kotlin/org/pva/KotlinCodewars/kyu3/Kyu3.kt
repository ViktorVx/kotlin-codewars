package org.pva.KotlinCodewars.kyu3


fun parseRegexp(s: String):String {
    if (s.length == 1) return "Normal(\'$s\')"
    if (!s.contains(Regex("\\.|\\*|\\|"))) {
        var ins = s.replace(Regex("\\(|\\)"), "")
        ins = ins.toCharArray().map { "Normal(\'$it\')" }.joinToString(", ")
        return "Str ([$ins])"
    }
    return ""
}

