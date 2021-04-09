package com.github.sukhinin.simpleconsole

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun erase(n: Int) {
    print("\b".repeat(n))
}


fun pad(string: String, width: Int, pad: Char = ' '): String {
    val count = width - string.length
    return when {
        count > 0 -> string + pad.toString().repeat(count)
        else -> string.substring(0, width)
    }
}

fun timestamp(instant: Instant = Instant.now()): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss").withZone(ZoneId.systemDefault())
    return formatter.format(instant)
}
