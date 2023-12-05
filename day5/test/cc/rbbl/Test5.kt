package cc.rbbl

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class Test5 {

    val input = """
        seeds: 79 14 55 13

        seed-to-soil map:
        50 98 2
        52 50 48

        soil-to-fertilizer map:
        0 15 37
        37 52 2
        39 0 15

        fertilizer-to-water map:
        49 53 8
        0 11 42
        42 0 7
        57 7 4

        water-to-light map:
        88 18 7
        18 25 70

        light-to-temperature map:
        45 77 23
        81 45 19
        68 64 13

        temperature-to-humidity map:
        0 69 1
        1 0 69

        humidity-to-location map:
        60 56 37
        56 93 4
    """.trimIndent()

    @Test
    fun inputParsingTest() {
        val result = parseInput(input)
        assertEquals(79, result.seeds.first(), "first seed not equal")
        assertEquals(13, result.seeds.last(), "first seed not equal")
        assertEquals(RangeIndicator(98,50, 2), result.maps.first().first(), "first seed soil result is not equal")
        assertEquals(RangeIndicator(50, 52,48), result.maps.first().last(), "second seed soil result is not equal")
        assertEquals(RangeIndicator(56,60,37), result.maps.last().first(), "first humidity result is not equal")
        assertEquals(RangeIndicator(93, 56,4), result.maps.last().last(), "first humidity result is not equal")
    }

    @Test
    fun part1Test(){
        assertEquals(35, day5part1(parseInput(input)))
    }

    @Test
    fun part2Test(){
        assertEquals(46, day5part2(parseInput(input)))
    }

    @Test
    fun part2Test2(){
        assertEquals(46, day5part2(Instructions(listOf(82L,1L), parseInput(input).maps)))
    }

    @Test
    fun sourceRange(){
        assertEquals(98, RangeIndicator(98,50,2).sourceRange.first)
        assertEquals(99, RangeIndicator(98,50,2).sourceRange.last)
    }
}