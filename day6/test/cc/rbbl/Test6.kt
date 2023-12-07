package cc.rbbl

import kotlin.test.Test
import kotlin.test.assertEquals

class Test6 {
    val input = """
        Time:      7  15   30
        Distance:  9  40  200
    """.trimIndent()

    @Test
    fun part1Test() {
        assertEquals(288, day6calulation(parseForPart1(input)))
    }

    @Test
    fun part2Test() {
        assertEquals(71503, day6calulation(parseForPart2(input)))
    }
}