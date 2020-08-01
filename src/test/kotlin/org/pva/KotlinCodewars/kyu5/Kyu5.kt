package org.pva.KotlinCodewars.kyu5

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Kyu5 {

    private fun testing(n:Int, expect:String) {
        val actual = decomp(n)
        assertEquals(expect, actual)
    }
    @Test
    fun test() {
        testing(17, "2^15 * 3^6 * 5^3 * 7^2 * 11 * 13 * 17")
        testing(5, "2^3 * 3 * 5")
        testing(22, "2^19 * 3^9 * 5^4 * 7^3 * 11^2 * 13 * 17 * 19")
        testing(14, "2^11 * 3^5 * 5^2 * 7^2 * 11 * 13")
        testing(25, "2^22 * 3^10 * 5^6 * 7^3 * 11^2 * 13 * 17 * 19 * 23")
        testing(3319, "2^3310 * 3^1654 * 5^827 * 7^551 * 11^330 * 13^275 * 17^206 * 19^183 * 23^150 * 29^117 * 31^110 * 37^91 * 41^81 * 43^78 * 47^71 * 53^63 * 59^56 * 61^54 * 67^49 * 71^46 * 73^45 * 79^42 * 83^39 * 89^37 * 97^34 * 101^32 * 103^32 * 107^31 * 109^30 * 113^29 * 127^26 * 131^25 * 137^24 * 139^23 * 149^22 * 151^21 * 157^21 * 163^20 * 167^19 * 173^19 * 179^18 * 181^18 * 191^17 * 193^17 * 197^16 * 199^16 * 211^15 * 223^14 * 227^14 * 229^14 * 233^14 * 239^13 * 241^13 * 251^13 * 257^12 * 263^12 * 269^12 * 271^12 * 277^11 * 281^11 * 283^11 * 293^11 * 307^10 * 311^10 * 313^10 * 317^10 * 331^10 * 337^9 * 347^9 * 349^9 * 353^9 * 359^9 * 367^9 * 373^8 * 379^8 * 383^8 * 389^8 * 397^8 * 401^8 * 409^8 * 419^7 * 421^7 * 431^7 * 433^7 * 439^7 * 443^7 * 449^7 * 457^7 * 461^7 * 463^7 * 467^7 * 479^6 * 487^6 * 491^6 * 499^6 * 503^6 * 509^6 * 521^6 * 523^6 * 541^6 * 547^6 * 557^5 * 563^5 * 569^5 * 571^5 * 577^5 * 587^5 * 593^5 * 599^5 * 601^5 * 607^5 * 613^5 * 617^5 * 619^5 * 631^5 * 641^5 * 643^5 * 647^5 * 653^5 * 659^5 * 661^5 * 673^4 * 677^4 * 683^4 * 691^4 * 701^4 * 709^4 * 719^4 * 727^4 * 733^4 * 739^4 * 743^4 * 751^4 * 757^4 * 761^4 * 769^4 * 773^4 * 787^4 * 797^4 * 809^4 * 811^4 * 821^4 * 823^4 * 827^4 * 829^4 * 839^3 * 853^3 * 857^3 * 859^3 * 863^3 * 877^3 * 881^3 * 883^3 * 887^3 * 907^3 * 911^3 * 919^3 * 929^3 * 937^3 * 941^3 * 947^3 * 953^3 * 967^3 * 971^3 * 977^3 * 983^3 * 991^3 * 997^3 * 1009^3 * 1013^3 * 1019^3 * 1021^3 * 1031^3 * 1033^3 * 1039^3 * 1049^3 * 1051^3 * 1061^3 * 1063^3 * 1069^3 * 1087^3 * 1091^3 * 1093^3 * 1097^3 * 1103^3 * 1109^2 * 1117^2 * 1123^2 * 1129^2 * 1151^2 * 1153^2 * 1163^2 * 1171^2 * 1181^2 * 1187^2 * 1193^2 * 1201^2 * 1213^2 * 1217^2 * 1223^2 * 1229^2 * 1231^2 * 1237^2 * 1249^2 * 1259^2 * 1277^2 * 1279^2 * 1283^2 * 1289^2 * 1291^2 * 1297^2 * 1301^2 * 1303^2 * 1307^2 * 1319^2 * 1321^2 * 1327^2 * 1361^2 * 1367^2 * 1373^2 * 1381^2 * 1399^2 * 1409^2 * 1423^2 * 1427^2 * 1429^2 * 1433^2 * 1439^2 * 1447^2 * 1451^2 * 1453^2 * 1459^2 * 1471^2 * 1481^2 * 1483^2 * 1487^2 * 1489^2 * 1493^2 * 1499^2 * 1511^2 * 1523^2 * 1531^2 * 1543^2 * 1549^2 * 1553^2 * 1559^2 * 1567^2 * 1571^2 * 1579^2 * 1583^2 * 1597^2 * 1601^2 * 1607^2 * 1609^2 * 1613^2 * 1619^2 * 1621^2 * 1627^2 * 1637^2 * 1657^2 * 1663 * 1667 * 1669 * 1693 * 1697 * 1699 * 1709 * 1721 * 1723 * 1733 * 1741 * 1747 * 1753 * 1759 * 1777 * 1783 * 1787 * 1789 * 1801 * 1811 * 1823 * 1831 * 1847 * 1861 * 1867 * 1871 * 1873 * 1877 * 1879 * 1889 * 1901 * 1907 * 1913 * 1931 * 1933 * 1949 * 1951 * 1973 * 1979 * 1987 * 1993 * 1997 * 1999 * 2003 * 2011 * 2017 * 2027 * 2029 * 2039 * 2053 * 2063 * 2069 * 2081 * 2083 * 2087 * 2089 * 2099 * 2111 * 2113 * 2129 * 2131 * 2137 * 2141 * 2143 * 2153 * 2161 * 2179 * 2203 * 2207 * 2213 * 2221 * 2237 * 2239 * 2243 * 2251 * 2267 * 2269 * 2273 * 2281 * 2287 * 2293 * 2297 * 2309 * 2311 * 2333 * 2339 * 2341 * 2347 * 2351 * 2357 * 2371 * 2377 * 2381 * 2383 * 2389 * 2393 * 2399 * 2411 * 2417 * 2423 * 2437 * 2441 * 2447 * 2459 * 2467 * 2473 * 2477 * 2503 * 2521 * 2531 * 2539 * 2543 * 2549 * 2551 * 2557 * 2579 * 2591 * 2593 * 2609 * 2617 * 2621 * 2633 * 2647 * 2657 * 2659 * 2663 * 2671 * 2677 * 2683 * 2687 * 2689 * 2693 * 2699 * 2707 * 2711 * 2713 * 2719 * 2729 * 2731 * 2741 * 2749 * 2753 * 2767 * 2777 * 2789 * 2791 * 2797 * 2801 * 2803 * 2819 * 2833 * 2837 * 2843 * 2851 * 2857 * 2861 * 2879 * 2887 * 2897 * 2903 * 2909 * 2917 * 2927 * 2939 * 2953 * 2957 * 2963 * 2969 * 2971 * 2999 * 3001 * 3011 * 3019 * 3023 * 3037 * 3041 * 3049 * 3061 * 3067 * 3079 * 3083 * 3089 * 3109 * 3119 * 3121 * 3137 * 3163 * 3167 * 3169 * 3181 * 3187 * 3191 * 3203 * 3209 * 3217 * 3221 * 3229 * 3251 * 3253 * 3257 * 3259 * 3271 * 3299 * 3301 * 3307 * 3313 * 3319")
    }
}