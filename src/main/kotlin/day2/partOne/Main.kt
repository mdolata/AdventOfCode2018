package day2.partOne

import input.InputFile

fun main(args: Array<String>) {
    val sum2 = InputFile()
            .getFile("day2\\input_part_1.in")
            .readLines()
            .filter { word -> isAnyCharExistsTwice(word) }
            .count()

    val sum3 = InputFile()
            .getFile("day2\\input_part_1.in")
            .readLines()
            .filter { word -> isAnyCharExistsTriple(word) }
            .count()

    println(sum2 * sum3)
}

fun isAnyCharExistsTwice(word: String): Boolean {
    return isAnyCharExistsNTimes(word, 2)
}

fun isAnyCharExistsTriple(word: String): Boolean {
    return isAnyCharExistsNTimes(word, 3)
}


private fun isAnyCharExistsNTimes(word: String, counter: Int) =
        word.groupBy { c: Char -> c.toInt() }.values.filter { v -> v.size == counter }.count() > 0
