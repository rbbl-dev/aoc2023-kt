package cc.rbbl

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

suspend fun fetchPuzzleInput(day: Int): String {
    val client = HttpClient(CIO)
    val response = client.get("https://adventofcode.com/2023/day/${day}/input") {
        headers["Cookie"] = "session=${System.getenv("AOC_SESSION_COOKIE")}"
    }
    if(response.status != HttpStatusCode.OK) {
        throw IllegalArgumentException("needs AOC_SESSION_COOKIE environment variable")
    }
    return response.bodyAsText()
}