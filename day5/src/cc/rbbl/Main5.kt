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
    val part2: Long
    val part2TimeMs = measureTimeMillis {
        part2 = day5part2(input)
    }
    println("day5 part2: $part2 | solved in $part2TimeMs ms")
}

fun day5part1(input: Instructions): Long {
    var currentNumbers = input.seeds
    input.maps.forEach { map ->
        val newNumbers = mutableListOf<Long>()
        currentNumbers.forEach {
            var hasMatch = false
            map.forEach { rangeIndicator ->
                if (it in rangeIndicator.source..rangeIndicator.source + rangeIndicator.amount) {
                    newNumbers.add(rangeIndicator.target + (it - rangeIndicator.source))
                    hasMatch = true
                }
            }
            if (!hasMatch) {
                newNumbers.add(it)
            }
        }
        currentNumbers = newNumbers
    }
    return currentNumbers.min()
}

fun day5part2(input: Instructions): Long {
    var currentRanges = input.seeds.chunked(2).map { it[0]..<it[0] + it[1] }.toSet()
    for(map in input.maps){
        val newLeftovers = mutableSetOf<LongRange>()
        val newRanges = mutableSetOf<LongRange>()
        for( range in currentRanges) {
            var innerLeftovers = setOf<LongRange>(range)
            map.forEach { rangeIndicator ->
                val potentialLeftovers = mutableSetOf<LongRange>()
                innerLeftovers.forEach { leftover ->
                    val result = leftover.map(rangeIndicator)
                    result.migrated?.let {
                        newRanges.add(it)
                    }
                    potentialLeftovers.addAll(result.leftovers)
                }
                innerLeftovers =potentialLeftovers
            }
            newLeftovers.addAll(innerLeftovers)
        }
        currentRanges = (newRanges+newLeftovers)
    }
    return currentRanges.map { it.first }.min()
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

fun LongRange.map(rangeIndicator: RangeIndicator): MappingResult {
    return if (this.first < rangeIndicator.sourceRange.first && this.last > rangeIndicator.sourceRange.last) {
        MappingResult(
            rangeIndicator.targetRange,
            listOf(this.first..<rangeIndicator.sourceRange.last, rangeIndicator.sourceRange.last + 1..this.last)
        )
    } else if (this.first < rangeIndicator.sourceRange.first && this.last < rangeIndicator.sourceRange.first) {
        MappingResult(null, this)
    } else if (this.first < rangeIndicator.sourceRange.first && this.last <= rangeIndicator.sourceRange.last) {
        MappingResult(
            rangeIndicator.targetRange.first..rangeIndicator.targetRange.last - (rangeIndicator.sourceRange.last - this.last),
            listOf(this.first..<rangeIndicator.sourceRange.first)
        )
    } else if (this.first >= rangeIndicator.sourceRange.first && this.last <= rangeIndicator.sourceRange.last) {
        MappingResult(
            rangeIndicator.targetRange.first + (this.first - rangeIndicator.sourceRange.first)..rangeIndicator.targetRange.last - (rangeIndicator.sourceRange.last - this.last),
            listOf()
        )
    } else if (this.first > rangeIndicator.sourceRange.last && this.last > rangeIndicator.sourceRange.last) {
        MappingResult(null, this)
    } else if (this.first >= rangeIndicator.sourceRange.first && this.last > rangeIndicator.sourceRange.last) {
        MappingResult(
            rangeIndicator.targetRange.first + (this.first - rangeIndicator.sourceRange.first)..rangeIndicator.targetRange.last,
            rangeIndicator.sourceRange.last + 1..this.last
        )
    } else {
        throw IllegalArgumentException()
    }
}

data class MappingResult(
    val migrated: LongRange?,
    val leftovers: List<LongRange>
) {
    constructor(migrated: LongRange?, leftover: LongRange) : this(migrated, listOf(leftover))
}

data class Instructions(
    val seeds: List<Long>,
    val maps: List<List<RangeIndicator>>
)

data class RangeIndicator(
    val source: Long,
    val target: Long,
    val amount: Long
) {
    val sourceRange = source..<source + amount
    val targetRange = target..<target + amount
}