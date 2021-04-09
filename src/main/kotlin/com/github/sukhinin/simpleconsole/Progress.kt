package com.github.sukhinin.simpleconsole

class Progress private constructor(title: String, private val total: Int) : AutoCloseable {

    companion object {
        fun <R> track(title: String, total: Int, block: (Progress) -> R): R {
            return Progress(title, total).use { block(it) }
        }
    }

    private val progressBarLength: Int = 20
    private var currentStatusLength: Int = 0
    private var success: Int = 0
    private var fail: Int = 0

    init {
        fprint("[${timestamp()}] $title: ", Color.PRIMARY)
        fflush()
    }

    @Synchronized
    fun success() {
        update(success + 1, fail)
    }

    @Synchronized
    fun fail() {
        update(success, fail + 1)
    }

    private fun update(success: Int, fail: Int) {
        this.success = success
        this.fail = fail

        if (currentStatusLength > 0) {
            erase(progressBarLength + currentStatusLength + 3)
        }

        val successBars = progressBarLength * success / total
        val failBars = progressBarLength * fail / total
        val emptyBars = progressBarLength - (successBars + failBars)
        val text = "S:$success F:$fail T:$total"
        fprint("[", Color.PRIMARY)
        fprint("=".repeat(successBars), Color.SUCCESS)
        fprint("=".repeat(failBars), Color.ERROR)
        fprint(" ".repeat(emptyBars), Color.INFO)
        fprint("] $text", Color.PRIMARY)
        fflush()
        currentStatusLength = text.length
    }

    override fun close() {
        // No action required
    }
}
