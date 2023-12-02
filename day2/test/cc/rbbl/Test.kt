package cc.rbbl

import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    @Test
    fun part1Test(){
        val red = 12
        val green = 13
        val blue = 14

        val input = """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
        """.trimIndent()
        assertEquals(8, day2part1(input, red, green, blue))
    }
}