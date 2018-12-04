package day3.partTwo

import spock.lang.Specification

import java.util.stream.Collectors

import static day3.partTwo.MainKt.idWithoutOverlap


class MainKtTest extends Specification {
    def "IdWithoutOverlap"() {
        given:
        def claims = input.stream().map{ line -> new Claim(line)}.collect(Collectors.toList())
        when:
        def claimWithoutOverlap = idWithoutOverlap(claims)
        then:
        assert claimWithoutOverlap.num == expect


        where:
        input            | expect
        ["#1 @ 1,3: 4x4",
         "#2 @ 3,1: 4x4",
         "#3 @ 5,5: 2x2",] | 3
    }
}
