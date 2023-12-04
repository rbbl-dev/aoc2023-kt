package cc.rbbl

suspend fun main() = day4()

suspend fun day4() {
    val input = parseInput(fetchPuzzleInput(4))
    println("day4 part1: ${day4part1(input)}")
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