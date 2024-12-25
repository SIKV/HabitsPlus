package com.github.sikv.habitsplus.ui.feature.activity.add

import android.content.Context
import com.github.sikv.habitsplus.R
import com.github.sikv.habitsplus.feature.activity.add.AddActivityError

fun AddActivityError.localizedMessage(context: Context): String {
    return when (this) {
        AddActivityError.Unknown -> context.getString(R.string.add_activity_error)
        AddActivityError.EmptyDescription -> context.getString(R.string.add_activity_error_empty_description)
    }
}
