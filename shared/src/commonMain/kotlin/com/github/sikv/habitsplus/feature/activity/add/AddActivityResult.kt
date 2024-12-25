package com.github.sikv.habitsplus.feature.activity.add

sealed class AddActivityResult {

    data object Success : AddActivityResult()

    data class Failure(
        val error: AddActivityError = AddActivityError.Unknown
    ) : AddActivityResult()
}
