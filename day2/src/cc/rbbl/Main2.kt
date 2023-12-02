package cc.rbbl

suspend fun main() {
    println(day2part1(fetchPuzzleInput(2), 12,13,14))
}

fun day2part1(input: String, red: Int, green: Int, blue: Int): Int {
    val parsedInput= parseInput(input)
    return parsedInput.sumOf { gameResult ->
        gameResult.result.forEach {grabResult ->
            if(red < grabResult.red || green < grabResult.green || blue < grabResult.blue) {
                return@sumOf 0
            }
        }
        gameResult.id
    }
}

fun parseInput(input: String): List<GameResult> {
    return input.trim().lines().map {line ->
        val idAndResults = line.replaceFirst("Game ", "").split(":")
        val results = idAndResults[1].split(";")
        val parsedResults: List<GrabResult> = results.map {grabResult ->
            var red = 0
            var green = 0
            var blue = 0
            grabResult.split(",").map { it.trim() }.forEach {singleResult ->
                val numberAndColor = singleResult.split(" ")
                when{
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