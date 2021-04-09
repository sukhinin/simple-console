package com.github.sukhinin.simpleconsole

class Status private constructor(title: String) : AutoCloseable {

    companion object {
        fun <R> track(title: String, block: (Status) -> R) {
            return Status(title).use { block(it) }
        }
    }

    private var currentStatusLength: Int = 0

    init {
        fprint("[${timestamp()}] $title: ", Color.PRIMARY)
        fflush()
    }

    @Synchronized
    fun set(text: String, color: Color) {
        erase(currentStatusLength)
        fprint(text, color)
        fflush()
        currentStatusLength = text.length
    }

    override fun close() {
        when (currentStatusLength) {
            0 -> fprintln("<undefined>", Color.WARN)
            else -> fprintln("", Color.INFO)
        }
    }
}
