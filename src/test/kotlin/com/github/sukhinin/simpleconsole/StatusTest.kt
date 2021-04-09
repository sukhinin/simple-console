package com.github.sukhinin.simpleconsole

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.string.shouldContain
import io.mockk.*

internal class StatusTest : ShouldSpec({

    mockkStatic("com.github.sukhinin.simpleconsole.ConsoleKt")

    afterEach { clearAllMocks() }
    afterSpec { unmockkAll() }

    should("print status title") {
        val title = "status title"
        Status.track(title) {}
        verify { fprint(withArg { it shouldContain title }, Color.PRIMARY) }
    }

    should("print status text") {
        val text = "status text"
        Status.track("title") { s -> s.set(text, Color.INFO) }
        verify { fprint(withArg { it shouldContain text }, Color.INFO) }
    }

    should("print warning when no status text is set") {
        Status.track("title") {}
        verify { fprint(any(), Color.WARN) }
    }

})
