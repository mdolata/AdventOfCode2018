package day1.partTwo

import input.InputFile
import kotlin.streams.toList

fun main(args: Array<String>) {
    val lines = InputFile()
            .getFile("day1\\input_part_1.in")
            .readLines()
            .stream()
            .mapToInt(Integer::parseInt)
            .toList()

    println(calculate(lines))
}

fun calculate(lines: List<Int>): Int {
    val frequencies = mutableSetOf<Int>()
    var current = 0
    var i = 0
    do {
        frequencies.add(current)
        val change = lines[i++ % lines.size]
        current += change
    } while (!frequencies.contains(current))
    return current
}