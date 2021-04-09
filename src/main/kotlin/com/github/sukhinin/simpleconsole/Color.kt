package com.github.sukhinin.simpleconsole

enum class Color(val value: String) {
    INFO(ANSI_BRIGHT_WHITE),
    WARN(ANSI_BRIGHT_YELLOW),
    ERROR(ANSI_BRIGHT_RED),
    SUCCESS(ANSI_BRIGHT_GREEN),

    PRIMARY(ANSI_WHITE),
    SECONDARY(ANSI_BRIGHT_BLACK)
}
