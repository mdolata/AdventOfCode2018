package day1.partOne

import input.InputFile

fun main(args: Array<String>) {
    val sum = InputFile()
            .getFile("day1\\input_part_1.in")
            .readLines()
            .stream()
            .mapToInt(Integer::parseInt)
            .sum()

    println(sum)
}