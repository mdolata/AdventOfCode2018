package day2.partOne

import spock.lang.Specification


class MainKtTest extends Specification {
    def "IsAnyCharExistsTwice"() {
        when:
        def result = MainKt.isAnyCharExistsTwice(word)
        then:
        assert result == expect

        where:
        word     | expect
        "abcdef" | false
        "bababc" | true
        "abbcde" | true
        "abcccd" | false
        "aabcdd" | true
        "abcdee" | true
        "ababab" | false
    }

    def "IsAnyCharExistsTriple"() {
        when:
        def result = MainKt.isAnyCharExistsTriple(word)
        then:
        assert result == expect

        where:
        word     | expect
        "abcdef" | false
        "bababc" | true
        "abbcde" | false
        "abcccd" | true
        "aabcdd" | false
        "abcdee" | false
        "ababab" | true
    }
}
