package cc.rbbl

suspend fun main() {
  println(day1(fetchPuzzleInput(1)))
}

fun day1(input: String): Int {
  return input.trim().lines().sumOf { line ->
    val numbers = line.replace(Regex("\\D"), "")
    "${numbers.first()}${numbers.last()}".toInt()
  }
}