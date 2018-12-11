package day4.partOne

import input.InputFile
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import java.util.stream.Stream
import kotlin.streams.toList

fun main(args: Array<String>) {
    var stream = InputFile()
            .getFile("day4\\input.in")
            .readLines()
            .stream()

    val records = fillOutGuards(prepareList(stream))

    val mostAsleepGuard: String
    mostAsleepGuard = mostAsleepGuard(records)
    println(mostAsleepHourForGuard(records, mostAsleepGuard))

    val result = guardMostFrequentlyAsleepOnTheSameMinute(records)
    println(result)

    println(result.first.toInt() * result.second)

}

fun guardMostFrequentlyAsleepOnTheSameMinute(records: List<Triple<Event, LocalDateTime, String>>): Pair<String, Int> {
    val maxBy = records.map { it.third }
            .distinct()
            .map { Pair(it, minutesMap(records, it)) }
            .map { Pair(it.first, getMaxValue(it.second)) }
            .maxBy { it.second.second }
    val maxBy1: Pair<String, Pair<Int, Int>> = maxBy ?: Pair("", Pair(0,0))

    return Pair(maxBy1.first, maxBy1.second.first)
}

fun getMaxValue(map: MutableMap<Int, Int>): Pair<Int, Int> {
    return map
            .toList()
            .sortedBy { -it.second }
            .getOrElse(0) { 0 to 0 }
}

fun prepareList(records: Stream<String>): List<Triple<Event, LocalDateTime, String>> {
    return records.map { line -> Record(line) }
            .toList()
            .sortedWith(compareBy { it.date })
            .map { record -> parse(record) }

}

fun fillOutGuards(records: List<Triple<Event, LocalDateTime, String>>): List<Triple<Event, LocalDateTime, String>> {
    var currentGuard = ""
    val list = mutableListOf<Triple<Event, LocalDateTime, String>>()
    for (record in records) {
        if (record.first == Event.START) {
            currentGuard = record.third
            list.add(record)
            continue
        }
        list.add(Triple(record.first, record.second, currentGuard))
    }
    return list
}

fun mostAsleepHourForGuard(records: List<Triple<Event, LocalDateTime, String>>, guard: String): Int {
    val map = minutesMap(records, guard)

    return map.toList().sortedBy { (_, value) -> -value }.first().first
}

private fun minutesMap(records: List<Triple<Event, LocalDateTime, String>>, guard: String): MutableMap<Int, Int> {
    val list = records
            .filter { it.third == guard }
            .filter { it.first != Event.START }

    val mutableMapOf = mutableMapOf<LocalTime, Int>()
    var currentTime = LocalTime.now()
    for (r in list) {
        if (r.first == Event.SLEEP) {
            currentTime = r.second.toLocalTime()
            continue
        }

        while (currentTime.isBefore(r.second.toLocalTime())) {
            val orDefault = mutableMapOf.getOrDefault(currentTime, 0)
            mutableMapOf.put(currentTime, orDefault + 1)
            currentTime = currentTime.plusMinutes(1)
        }
    }

    val list1 = mutableMapOf
            .toList()
            .map { Pair(it.first.minute, it.second) }

    val map = mutableMapOf<Int, Int>()

    for (r in list1) {
        val orDefault = map.getOrDefault(r.first, 0)
        map.put(r.first, orDefault + r.second)

    }
    return map
}


// map : guard -> sleep long
fun mostAsleepGuard(records: List<Triple<Event, LocalDateTime, String>>): String {
    val map = mutableMapOf<String, Long>()

    var currentStart = LocalDateTime.MIN
    for (record in records) {
        if (record.first == Event.SLEEP) {
            currentStart = record.second
        } else if (record.first == Event.WAKEUP) {
            val minutes = ChronoUnit.MINUTES.between(currentStart, record.second)
            val hours = ChronoUnit.HOURS.between(currentStart, record.second)
            val sum = minutes + hours * 60

            val orDefault = map.getOrDefault(record.third, 0L)

            map.put(record.third, orDefault + sum)
        }
    }


    return map.toList().sortedBy { (_, value) -> -value }.first().first
}

// event, event date, guard
fun parse(record: Record): Triple<Event, LocalDateTime, String> {
    return Triple(event(record.message), record.date, guardNum(record.message))
}

fun guardNum(message: String): String {
    return if (message.contains("Guard"))
        message.removePrefix("Guard #").removeSuffix(" begins shift")
    else {
        ""
    }
}

enum class Event {
    START, SLEEP, WAKEUP
}

fun event(message: String): Event {
    return when {
        message.contains("begins") -> Event.START
        message.contains("falls") -> Event.SLEEP
        message.contains("wakes up") -> Event.WAKEUP
        else -> throw RuntimeException("event not found")
    }
}

class Record(line: String) {

    val date: LocalDateTime
    val message: String

    init {
        val split = line.split("] ")
        val dateS = split[0].replace("[", "").split(" ")
        val split1 = dateS[0].split("-")
        val split2 = dateS[1].split(":")
        date = getLocalDateTime(split1, split2)
        message = split[1]

    }

    private fun getLocalDateTime(split1: List<String>, split2: List<String>): LocalDateTime {
        val year = Integer.valueOf(split1[0])
        val month = Integer.valueOf(split1[1])
        val day = Integer.valueOf(split1[2])

        val localDate = LocalDate.of(year, month, day)

        val hour = Integer.valueOf(split2[0])
        val minute = Integer.valueOf(split2[1])

        val localTime = LocalTime.of(hour, minute)

        return LocalDateTime.of(localDate, localTime)
    }

    override fun toString(): String {
        return "Record(date=$date, message='$message')"
    }

}