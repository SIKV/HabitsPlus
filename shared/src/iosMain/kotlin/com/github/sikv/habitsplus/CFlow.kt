package com.github.sikv.habitsplus

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

// Source:
// https://github.com/Kotlin/kmp-production-sample/blob/master/shared/src/iosMain/kotlin/com/github/jetbrains/rssreader/core/CFlow.kt

fun interface Closeable {
    fun close()
}

class CFlow<T: Any> internal constructor(private val origin: Flow<T>) : Flow<T> by origin {
    fun watch(block: (T) -> Unit): Closeable {
        val job = Job()

        onEach {
            block(it)
        }.launchIn(CoroutineScope(Dispatchers.Main + job))

        return Closeable { job.cancel() }
    }
}

internal fun <T: Any> Flow<T>.wrap(): CFlow<T> = CFlow(this)
