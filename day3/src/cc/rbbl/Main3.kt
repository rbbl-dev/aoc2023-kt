package cc.rbbl

import kotlin.math.max
import kotlin.math.min

suspend fun main() = day3()

suspend fun day3() {
    val input = parseInput(fetchPuzzleInput(3))
    println("day3 part1: ${day3part1(input)}")
    println("day3 part2: ${day3part2(input)}")
}

fun day3part1(input: List<List<Char>>): Int {
    var currentNumber = 0
    var sum = 0
    for (y in 0..input.height()) {
        for (x in 0..input.width()) {
            if (input.checkNeighbors(x, y) {
                    it != '.' && !it.isDigit()
                }) {
                input.getContiguousNumber(x, y)?.let {num ->
                    if(num != currentNumber) {
                        currentNumber = num
                        sum += num
                    }
                }
            }else {
                currentNumber = 0
            }
        }
    }
    return sum
}

fun day3part2(input: List<List<Char>>): Int {
    var sum = 0
    input.forEachIndexed { x, charList ->
        charList.forEachIndexed { y, char ->
            if(char == '*') {
                val surroundingNumbers = mutableListOf<Int>()
                var currentNumber: Int? = null
                input.iterateNeighbors(x, y){charN, xN, yN ->
                    if(charN.isDigit()) {
                        val contiguousNumber = input.getContiguousNumber(xN, yN)
                        if(contiguousNumber != currentNumber) {
                            currentNumber = contiguousNumber
                            surroundingNumbers.add(contiguousNumber!!)
                        }
                    }else {
                        currentNumber = null
                    }
                }
                if(surroundingNumbers.size == 2) {
                    sum += surroundingNumbers[0] * surroundingNumbers[1]
                }
            }
        }
    }

    return sum
}

fun List<List<Char>>.getContiguousNumber(centerX:Int, centerY:Int): Int?{
    if(!this[centerX][centerY].isDigit()) {
        return null
    }
    val charArray: MutableList<Char> = mutableListOf()
    var numberStart = 0
    var numberEnd = 0
    for(x in centerX downTo 0) {
        if(!this[x][centerY].isDigit()) {
            break
        }
        numberStart = x
    }
    for(x in centerX..this.width()) {
        if(!this[x][centerY].isDigit()) {
            break
        }
        numberEnd = x
    }
    for(x in numberStart..numberEnd) {
        charArray.add(this[x][centerY])
    }
    return charArray.joinToString("","","").toInt()
}

fun List<List<Char>>.iterateNeighbors(centerX: Int, centerY: Int, apply: (char: Char, x: Int, y: Int) -> Unit ) {
    for (y in max(centerY - 1, 0)..min(centerY + 1, this.height())) {
        for (x in max(centerX - 1, 0)..min(centerX + 1, this.width())) {
            apply(this[x][y], x, y)
        }
    }
}

fun List<List<Char>>.checkNeighbors(centerX: Int, centerY: Int, matchFunction: (char: Char) -> Boolean): Boolean {
    for (y in max(centerY - 1, 0)..min(centerY + 1, this.height())) {
        for (x in max(centerX - 1, 0)..min(centerX + 1, this.width())) {
            if (x == centerX && y == centerY) {
                continue
            }
            if (matchFunction(this[x][y])) {
                return true
            }
        }
    }
    return false
}

fun List<List<Char>>.height() = this[0].size-1

fun List<List<Char>>.width() = this.size-1

fun parseInput(input: String): List<List<Char>> {
    val coordinateSystem: MutableList<MutableList<Char>> = mutableListOf()
    input.lines().forEachIndexed { y, line ->
        line.forEachIndexed { x, char ->
            if (y == 0) {
                coordinateSystem.add(mutableListOf())
            }
            coordinateSystem[x].add(char)
        }
    }
    return coordinateSystem
}

