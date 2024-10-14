package com.github.sikv.habitsplus.store

data class AppState(
    val placeholder: String,
): State {

    companion object {
        val emptyState = AppState(
            placeholder = "Hello, World!"
        )
    }
}
