package com.github.sukhinin.simpleconsole

fun fprintln(message: String, color: Color) {
    fprint(message, color)
    println()
}

fun fprint(message: String, color: Color) {
    fprint(message, color.value)
}

fun fflush() {
    print(' ')
    print('\b')
}

private fun fprint(message: String, color: String) {
    print("$color$message$ANSI_RESET")
}
