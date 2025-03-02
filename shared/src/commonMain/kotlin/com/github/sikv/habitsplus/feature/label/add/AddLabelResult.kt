package com.github.sikv.habitsplus.feature.label.add

sealed class AddLabelResult {

    data object Success : AddLabelResult()

    data class Failure(
        val error: AddLabelError = AddLabelError.Unknown
    ) : AddLabelResult()
}
