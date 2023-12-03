package cc.rbbl

import kotlin.test.Test
import kotlin.test.assertEquals

class Test {

    val input = """
        467..114..
        ...*......
        ..35..633.
        ......#...
        617*......
        .....+.58.
        ..592.....
        ......755.
        ...${'$'}.*....
        .664.598..
        """.trimIndent()

    @Test
    fun inputParsingTest() {
        val result = parseInput(input)
        assertEquals('4', result[0][0], "0,0 should be a 4")
        assertEquals('.', result[9][9], "9,9 should be a .")
    }

    @Test
    fun part1Test(){
        assertEquals(4361, day2part1(parseInput(input)))
    }
}