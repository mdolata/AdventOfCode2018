package day4.partOne

import spock.lang.Specification



import static day4.partOne.MainKt.fillOutGuards
import static day4.partOne.MainKt.guardMostFrequentlyAsleepOnTheSameMinute
import static day4.partOne.MainKt.mostAsleepGuard
import static day4.partOne.MainKt.mostAsleepHourForGuard
import static day4.partOne.MainKt.prepareList


class MainKtTest extends Specification {
    def "FindGuard"() {

        setup:
        def list = fillOutGuards(prepareList(input.stream()))

        when:
        def guard = mostAsleepGuard(list)
        then:
        assert guard == expectGuard

        when:
        def minute = mostAsleepHourForGuard(list, guard)
        then:
        assert minute == expectMinute


        when:
        def pair = guardMostFrequentlyAsleepOnTheSameMinute(list)
        then:
        assert pair.first == expectGuard2
        assert pair.second == expectMinute2


        where:
        input                                                | expectGuard | expectMinute | expectGuard2 | expectMinute2
        ["[1518-11-01 00:00] Guard #10 begins shift",
         "[1518-11-01 00:05] falls asleep",
         "[1518-11-01 00:25] wakes up",
         "[1518-11-01 00:30] falls asleep",
         "[1518-11-01 00:55] wakes up",
         "[1518-11-01 23:58] Guard #99 begins shift",
         "[1518-11-02 00:40] falls asleep",
         "[1518-11-02 00:50] wakes up",
         "[1518-11-03 00:05] Guard #10 begins shift",
         "[1518-11-03 00:24] falls asleep",
         "[1518-11-03 00:29] wakes up",
         "[1518-11-04 00:02] Guard #99 begins shift",
         "[1518-11-04 00:36] falls asleep",
         "[1518-11-04 00:46] wakes up",
         "[1518-11-05 00:03] Guard #99 begins shift",
         "[1518-11-05 00:45] falls asleep",
         "[1518-11-05 00:55] wakes up"]                     | "10" | 24 | "99" | 45

    }
}
