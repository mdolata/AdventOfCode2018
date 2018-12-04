package day3.partOne

import input.InputFile
import kotlin.streams.toList

fun main(args: Array<String>) {
    val claims = InputFile()
            .getFile("day3\\input_part_1.in")
            .readLines()
            .stream()
            .map { line -> Claim(line) }
            .toList()


    val howManySquares = howManySquares(claims)
    println(howManySquares)

}

fun howManySquares(claims: List<Claim>): Int {
    val fabric = createArray(claims)

    for (claim in claims) {
        markFabric(claim, fabric)
    }

    return fabric
            .map { arr -> arr
                    .filter { i -> i > 1 }
                    .count() }
            .filter { i -> i > 1 }
            .sum()
}

fun markFabric(claim: Claim, fabric: Array<Array<Int>>) {
    for (y in claim.y until claim.y + claim.height) {
        for (x in claim.x until claim.x + claim.width) {
            fabric[x][y]++
        }
    }
}

fun createArray(claims: List<Claim>): Array<Array<Int>> {
    val maxX = claims.stream().mapToInt { claim -> claim.x + claim.width }.max().orElse(0)
    val maxY = claims.stream().mapToInt { claim -> claim.y + claim.height }.max().orElse(0)

    //val arr: Array<Int> = Array(maxX) { 0 }
    val array: Array<Array<Int>> = Array(maxY) { arrayOf(1) }

    for (i in array.indices) {
        array[i] = Array(maxX) { 0 }
    }

    return array
}

class Claim(line: String) {
    val num: Int
    val x: Int
    val y: Int
    val width: Int
    val height: Int

    init {
        val split = line.split(" ")
        num = Integer.valueOf(split[0].replace("#", ""))
        val xy = split[2].replace(":", "").split(",")
        x = Integer.valueOf(xy[0])
        y = Integer.valueOf(xy[1])

        val wh = split[3].split("x")
        width = Integer.valueOf(wh[0])
        height = Integer.valueOf(wh[1])

    }
}