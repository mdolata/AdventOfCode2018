package day3.partOne

import spock.lang.Specification


class ClaimTest extends Specification {

    def "method should split correctly"() {
        given:
        def claim = new Claim(input)
        expect:
        assert claim.num == eNum
        assert claim.x == eX
        assert claim.y == eY
        assert claim.width == eW
        assert claim.height == eH

        where:
        input           | eNum | eX | eY | eW | eH
        "#1 @ 1,3: 4x4" | 1    | 1  | 3  | 4  | 4
        "#2 @ 3,1: 4x4" | 2    | 3  | 1  | 4  | 4
        "#3 @ 5,5: 2x2" | 3    | 5  | 5  | 2  | 2
    }
}
