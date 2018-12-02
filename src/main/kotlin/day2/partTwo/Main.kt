package day2.partTwo

import input.InputFile

fun main(args: Array<String>) {
    val list = InputFile()
            .getFile("day2\\input_part_1.in")
            .readLines()
            .toList()

    val findSimilarIDs = findSimilarIDs(list)

    println(findSimilarIDs)

    println(getCommonChars (findSimilarIDs))
}

fun getCommonChars(pair: Pair<String, String>): String {
    var result = ""
    for(c in 0 until pair.first.length)
        result += if(pair.first[c] == pair.second[c]) pair.first[c] else " "

    return result
}

fun findSimilarIDs(list: List<String>): Pair<String, String> {

    val sorted = list.sorted()

    for (word in sorted) {
        for (word2 in sorted.dropWhile { w -> w != word }) {
            if (wordsAreSimilar(word, word2))
                return Pair(word, word2)
        }
    }
    println(sorted)

    return Pair("first", "second")
}

fun wordsAreSimilar(word: String, word2: String): Boolean {
    var similars = 0
    for (pair in word.zip(word2).listIterator()) {
        if (pair.first == pair.second)
            similars++
    }

    return similars == word.length - 1
}
