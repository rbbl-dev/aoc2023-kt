package cc.rbbl

import kotlin.system.measureTimeMillis

suspend fun main() = day1()

suspend fun day1(){
  val input = fetchPuzzleInput(1)
  val part1: Int
  val part1TimeMs = measureTimeMillis {
    part1 = day1part1(input)
  }
  println("day1 part1: $part1 | solved in $part1TimeMs ms")
  val part2: Int
  val part2TimeMs = measureTimeMillis {
    part2 = day1part2(input)
  }
  println("day1 part2: $part2 | solved in $part2TimeMs ms")
}

fun day1part1(input: String): Int {
  return input.trim().lines().sumOf { line ->
    val numbers = line.replace(Regex("\\D"), "")
    "${numbers.first()}${numbers.last()}".toInt()
  }
}

fun day1part2(input: String): Int {
  return input.trim().lines().sumOf { line ->
    val numbers = line.getFirstAnLastNumber()
    "${numbers.first}${numbers.second}".toInt()
  }
}

fun String.getFirstAnLastNumber(): Pair<Int, Int> {
  val firstTextNumber = this.findAnyOf(wordNumberMap.keys)
  val firstActualNumber = this.findAnyOf(wordNumberMap.values)
  val lastTextNumber = this.findLastAnyOf(wordNumberMap.keys)
  val lastActualNumber = this.findLastAnyOf(wordNumberMap.values)

  val firstNumber = if(firstActualNumber != null && firstTextNumber != null) {
    if(firstActualNumber.first < firstTextNumber.first) {
      firstActualNumber.second.toInt()
    }else {
      wordNumberMap[firstTextNumber.second]!!.toInt()
    }
  }else firstActualNumber?.second?.toInt() ?: wordNumberMap[firstTextNumber!!.second]!!.toInt()

  val lastNumber = if(lastActualNumber != null && lastTextNumber != null) {
    if(lastActualNumber.first > lastTextNumber.first) {
      lastActualNumber.second.toInt()
    }else {
      wordNumberMap[lastTextNumber.second]!!.toInt()
    }
  }else lastActualNumber?.second?.toInt() ?: wordNumberMap[lastTextNumber!!.second]!!.toInt()

  return Pair(firstNumber, lastNumber)
}

val wordNumberMap = mapOf(
  "one" to "1",
  "two" to "2",
  "three" to "3",
  "four" to "4",
  "five" to "5",
  "six" to "6",
  "seven" to "7",
  "eight" to "8",
  "nine" to "9"
)