package org.pva.KotlinCodewars.kyu5

import java.math.BigInteger


fun decomp(m:Int): String = primeFactors(factorial(m, 1, BigInteger.ONE))
        .groupBy { it }
        .map { (k, v) -> if (v.size == 1) "$k" else "$k^${v.size}" }
        .joinToString(" * ")


fun factorial(m: Int, i: Int, res: BigInteger): BigInteger =
        if (m==i) res.multiply(i.toBigInteger()) else
            factorial(m, i.inc(), res.multiply(i.toBigInteger()))

fun primeFactors(n: BigInteger): MutableList<BigInteger> {
    val result = mutableListOf<BigInteger>()
    var c = 2.toBigInteger()
    var r: BigInteger = n
    while (r > BigInteger.ONE) {
        while (r.mod(c) == BigInteger.ZERO) {
            result.add(c)
            r = r.div(c)
        }
        c = c.nextProbablePrime()
    }
    return result
}

