package org.pva.KotlinCodewars.kyu5

import java.math.BigInteger


fun decomp(m:Int): String {
    return primeFactors(factorial(m)).groupBy { it }
            .map { (k, v) -> if (v.size == 1) "$k" else "$k^$v.size()" }.toString()
}

fun factorial(m: Int): BigInteger {
    var factorial = BigInteger.ONE
    for (i in 2..m) {
        factorial = factorial.multiply(BigInteger.valueOf(i.toLong()))
    }
    return factorial
}

fun primeFactors(n: BigInteger): MutableList<BigInteger> {
    var result = mutableListOf<BigInteger>()
    var c = BigInteger.TWO
    var r: BigInteger = n
    while (r > BigInteger.ONE) {
        while (r.mod(c) == BigInteger.ZERO) {
            result.add(c)
            r = r.div(c)
        }
        c++
    }
    return result
}

