package day1.partTwo

import spock.lang.Specification


class MainKtTest extends Specification {


    def calculate() {

        when:
        def result = MainKt.calculate(list)
        then:
        assert result == expect

        where:
        list                 | expect
        [1, -2, 3, 1]        | 2
        [+1, -1]             | 0
        [+3, +3, +4, -2, -4] | 10
        [-6, +3, +8, +5, -6] | 5
        [+7, +7, -2, -7, -4] | 14
    }
}
