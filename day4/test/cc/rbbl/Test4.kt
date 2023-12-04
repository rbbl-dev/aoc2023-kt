package cc.rbbl

import kotlin.test.Test
import kotlin.test.assertEquals

class Test4 {

    val input = """
        Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
        Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
        Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
        Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
        Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
        Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
    """.trimIndent()

    @Test
    fun inputParsingTest() {
        val result = parseInput(input)
        assertEquals(1, result[0].id, "id doesn't match")
        assertEquals(41, result[0].winningNumbers.first(), "first winning number doesn't match")
        assertEquals(17, result[0].winningNumbers.last(), "last winning number doesn't match")
        assertEquals(83, result[0].gambleNumbers.first(), "first gamble number doesn't match")
        assertEquals(53, result[0].gambleNumbers.last(), "last gamble number doesn't match")
    }

    @Test
    fun part1Test(){
        assertEquals(13, day4part1(parseInput(input)))
    }
}