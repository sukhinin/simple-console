package com.github.sukhinin.simpleconsole

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.string.shouldContain
import io.mockk.clearAllMocks
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import io.mockk.verify

internal class ProgressTest : ShouldSpec({

    mockkStatic("com.github.sukhinin.simpleconsole.ConsoleKt")

    afterEach { clearAllMocks() }
    afterSpec { unmockkAll() }

    should("print progress title") {
        val title = "progress title"
        Progress.track(title, 0) {}
        verify { fprint(withArg { it shouldContain title }, Color.PRIMARY) }
    }

    should("print progress text on success") {
        val title = "progress title"
        Progress.track(title, 10) { p -> p.success() }
        verify { fprint(withArg { it shouldContain "S:1 F:0 T:10" }, Color.PRIMARY) }
    }

    should("print progress text on fail") {
        val title = "progress title"
        Progress.track(title, 10) { p -> p.fail() }
        verify { fprint(withArg { it shouldContain "S:0 F:1 T:10" }, Color.PRIMARY) }
    }
})
