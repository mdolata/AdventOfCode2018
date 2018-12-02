package day2.partTwo

import spock.lang.Specification
import kotlin.Pair

class MainKtTest extends Specification {
    def "FindSimilarIDs"() {
        when:
        def result = MainKt.findSimilarIDs(list)
        then:
        assert expect == result

        where:
        list                                                            | expect
        ["abcde", "fghij", "klmno", "pqrst", "fguij", "axcye", "wvxyz"] | new Pair("fghij", "fguij")

    }

    def "wordsAreSimilar"(){
        when:
        def result = MainKt.wordsAreSimilar(word1, word2)
        then:
        assert expect == result

        where:
        word1 | word2| expect
        "fghij" | "fguij" | true
        "aghij" | "fguij" | false

    }
}
