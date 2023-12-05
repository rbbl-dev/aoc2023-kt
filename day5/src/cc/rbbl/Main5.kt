package cc.rbbl

import kotlin.system.measureTimeMillis

suspend fun main() = day5()

suspend fun day5() {
    val input = parseInput(fetchPuzzleInput(5))
    val part1: Long
    val part1TimeMs = measureTimeMillis {
        part1 = day5part1(input)
    }
    println("day5 part1: $part1 | solved in $part1TimeMs ms")
}

fun day5part1(input: Instructions): Long {
    var currentNumbers = input.seeds
    input.maps.forEach {map ->
        val newNumbers = mutableListOf<Long>()
        currentNumbers.forEach {
            var hasMatch = false
            map.forEach { rangeIndicator ->
                if(it in rangeIndicator.source..rangeIndicator.source+rangeIndicator.amount) {
                    newNumbers.add(rangeIndicator.target+(it-rangeIndicator.source))
                    hasMatch = true
                }
            }
            if(!hasMatch) {
                newNumbers.add(it)
            }
        }
        currentNumbers = newNumbers
    }
    return currentNumbers.min()
}

fun parseInput(input: String): Instructions {
    val dataChunks = input.trim().split(Regex("\n\n"))
    val seedData = dataChunks.first()
    val parsedSeedData =
        seedData.split(" ").takeLast(seedData.split(" ").count() - 1).filter { it.isNotBlank() }.map { it.toLong() }
    val rangeIndicators = dataChunks.takeLast(dataChunks.count() - 1).map { chunk ->
        chunk.lines().takeLast(chunk.lines().count() - 1).map { line ->
            val rangeIndicators = line.split(" ").filter { it.isNotBlank() }.map { it.trim().toLong() }
            if (rangeIndicators.size != 3) {
                throw IllegalArgumentException("there should always be 3 range indicators. had '${rangeIndicators.joinToString()}'")
            }
            RangeIndicator(rangeIndicators[1], rangeIndicators[0], rangeIndicators[2])
        }
    }
    return Instructions(parsedSeedData, rangeIndicators)
}

data class Instructions(
    val seeds: List<Long>,
    val maps: List<List<RangeIndicator>>
)
data class RangeIndicator (
    val source: Long,
    val target: Long,
    val amount: Long
)