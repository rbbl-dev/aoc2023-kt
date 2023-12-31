package cc.rbbl

import kotlin.system.measureTimeMillis

suspend fun main() = day2()

suspend fun day2(){
    val input = parseInput(fetchPuzzleInput(2))
    val part1: Int
    val part1TimeMs = measureTimeMillis {
        part1 = day2part1(input, 12, 13, 14)
    }
    println("day1 part1: $part1 | solved in $part1TimeMs ms")
    val part2: Int
    val part2TimeMs = measureTimeMillis {
        part2 = day2part2(input)
    }
    println("day1 part2: $part2 | solved in $part2TimeMs ms")
}

fun day2part1(input: List<GameResult>, red: Int, green: Int, blue: Int): Int {
    return input.sumOf { gameResult ->
        gameResult.result.forEach { grabResult ->
            if (red < grabResult.red || green < grabResult.green || blue < grabResult.blue) {
                return@sumOf 0
            }
        }
        gameResult.id
    }
}

fun day2part2(input: List<GameResult>): Int {
    return input.sumOf { gameResult ->
        var red = 0
        var green = 0
        var blue = 0
        gameResult.result.forEach { grabResult ->
            if (grabResult.red > red) {
                red = grabResult.red
            }
            if (grabResult.green > green) {
                green = grabResult.green
            }
            if (grabResult.blue > blue) {
                blue = grabResult.blue
            }
        }
        red * green * blue
    }
}

fun parseInput(input: String): List<GameResult> {
    return input.trim().lines().map { line ->
        val idAndResults = line.replaceFirst("Game ", "").split(":")
        val results = idAndResults[1].split(";")
        val parsedResults: List<GrabResult> = results.map { grabResult ->
            var red = 0
            var green = 0
            var blue = 0
            grabResult.split(",").map { it.trim() }.forEach { singleResult ->
                val numberAndColor = singleResult.split(" ")
                when {
                    numberAndColor[1] == "red" -> red = numberAndColor[0].toInt()
                    numberAndColor[1] == "green" -> green = numberAndColor[0].toInt()
                    numberAndColor[1] == "blue" -> blue = numberAndColor[0].toInt()
                    else -> throw IllegalArgumentException("illegal String $singleResult")
                }
            }
            GrabResult(red, green, blue)
        }
        GameResult(idAndResults[0].toInt(), parsedResults)
    }
}

data class GameResult(
    val id: Int,
    val result: List<GrabResult>
)

data class GrabResult(
    val red: Int,
    val green: Int,
    val blue: Int
)