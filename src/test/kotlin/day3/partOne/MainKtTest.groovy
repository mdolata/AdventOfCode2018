package day3.partOne

import spock.lang.Specification

import java.util.stream.Collectors

import static day3.partOne.MainKt.howManySquares


class MainKtTest extends Specification {
    def "HowManySquares"() {

        given:
        def claims = input.stream().map{ line -> new Claim(line)}.collect(Collectors.toList())
        when:
        def squares = howManySquares(claims)
        then:
        assert squares == expect


        where:
        input            | expect
        ["#1 @ 1,3: 4x4",
        "#2 @ 3,1: 4x4",
        "#3 @ 5,5: 2x2",] | 4
    }
}
