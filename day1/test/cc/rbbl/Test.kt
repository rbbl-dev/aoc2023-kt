package cc.rbbl

import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    val input ="""
        1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet
    """.trimIndent()
    @Test
    fun day1Test() {
        assertEquals(142, day1(input))
    }
}