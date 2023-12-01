package cc.rbbl

import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    val input1 ="""
        1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet
    """.trimIndent()

    @Test
    fun part1Test() {
        assertEquals(142, part1(input1))
    }

    val input2 = """
        two1nine
        eightwothree
        abcone2threexyz
        xtwone3four
        4nineeightseven2
        zoneight234
        7pqrstsixteen
    """.trimIndent()

    @Test
    fun part2Test() {
        assertEquals(281, part2(input2))
    }

    @Test
    fun getFirstAnLastNumber() {
        assertEquals(Pair(7, 9), "abseveninedef".getFirstAnLastNumber())
    }

    @Test
    fun getFirstAnLastNumber2() {
        assertEquals(Pair(3, 6), "rtkrbtthree8sixfoureight6".getFirstAnLastNumber())
    }
}