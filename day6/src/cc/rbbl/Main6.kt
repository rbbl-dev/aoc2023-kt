package cc.rbbl

import kotlin.system.measureTimeMillis

suspend fun main() = day6()

suspend fun day6() {
    val input = fetchPuzzleInput(6)
    val part1: Long
    val part1TimeMs = measureTimeMillis {
        part1 = day6calulation(parseForPart1(input))
    }
    println("day6 part1: $part1 | solved in $part1TimeMs ms")
    val part2: Long
    val part2TimeMs = measureTimeMillis {
        part2 = day6calulation(parseForPart2(input))
    }
    println("day6 part1: $part2 | solved in $part2TimeMs ms")
}

fun day6calulation(input: List<List<Long>>): Long {
    val possibilities: List<Long> = input[0].zip(input[1]).map { timeAndDistance ->
        val time = timeAndDistance.first
        val distance = timeAndDistance.second
        var possibilities = 0L
        for (speed in 1..<time) {
            if (speed * (time - speed) > distance) {
                possibilities += 1
            }else if(possibilities>0){
                break
            }
        }
        possibilities
    }
    return possibilities.fold(1) { acc: Long, l: Long -> acc * l }
}

fun parseForPart1(input: String): List<List<Long>> {
    return input.trim().lines().map { it.split(" ").filter { it.isNotBlank() }.mapNotNull { it.toLongOrNull() } }
}

fun parseForPart2(input: String): List<List<Long>> {
    return parseForPart1(input).map { line -> listOf(line.fold(""){ acc: String, l: Long -> acc+l }.toLong()) }
}