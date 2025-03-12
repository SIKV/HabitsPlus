package com.github.sikv.habitsplus.ui.feature.label.add

import android.content.Context
import com.github.sikv.habitsplus.R
import com.github.sikv.habitsplus.feature.label.add.AddLabelError

fun AddLabelError.localizedMessage(context: Context): String {
    return when (this) {
        AddLabelError.Unknown -> context.getString(R.string.add_label_error)
        AddLabelError.EmptyTitle -> context.getString(R.string.add_label_error_empty_title)
    }
}
