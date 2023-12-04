package cc.rbbl

import kotlin.system.measureTimeMillis

suspend fun main() = day4()

suspend fun day4() {
    val input = parseInput(fetchPuzzleInput(4))
    val part1: Int
    val part1TimeMs = measureTimeMillis {
        part1 = day4part1(input)
    }
    println("day4 part1: $part1 | solved in $part1TimeMs ms")
    val part2: Int
    val part2TimeMs = measureTimeMillis {
        part2 = day4part2(input)
    }
    println("day4 part2: $part2 | solved in $part2TimeMs ms")
}

fun day4part1(input: List<Card>): Int {
    return input.sumOf { card ->
        var hits = 0
        card.winningNumbers.forEach { winningNumber ->
            if(card.gambleNumbers.contains(winningNumber)) {
                hits += 1
            }
        }
        var result = 0
        for(i in 0..hits) {
            if(i == 1) {
                result = 1
            }else {
                result *= 2
            }
        }
        result
    }
}

fun day4part2(input: List<Card>): Int {
    val additionalCards = mutableListOf<Card>()
    input.forEachIndexed {index, card ->
        var hits = 0
        card.winningNumbers.forEach { winningNumber ->
            if(card.gambleNumbers.contains(winningNumber)) {
                hits += 1
            }
        }
        val additionalCardCount = additionalCards.count { it.id == card.id }
        for(i in 1..hits) {
            for(l in 0..additionalCardCount) {
                additionalCards.add(input[index+i])
            }
        }
    }
    return input.count() + additionalCards.count()
}

fun parseInput(input: String): List<Card> {
    return input.trim().lines().map { line ->
        val id = line.split(":")[0].split(" ").filter { it.isNotBlank() }[1].toInt()
        val numberStings = line.split(":")[1]
        val splitNumberStrings = numberStings.split("|")
        val winningNumbers = splitNumberStrings[0].trim().split(" ").filter { it.isNotBlank() }.map { it.toInt() }
        val gambleNumbers = splitNumberStrings[1].trim().split(" ").filter { it.isNotBlank() }.map { it.toInt() }
        Card(id, winningNumbers, gambleNumbers)
    }
}

data class Card(
    val id: Int,
    val winningNumbers: List<Int>,
    val gambleNumbers: List<Int>
)