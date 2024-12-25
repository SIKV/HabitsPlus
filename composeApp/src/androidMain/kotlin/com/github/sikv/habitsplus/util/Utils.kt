package com.github.sikv.habitsplus.util

import android.content.Context
import android.text.format.DateUtils
import com.github.sikv.habitsplus.R
import com.github.sikv.habitsplus.data.model.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(context: Context, date: Timestamp?): String {
    return if (date != null) {
        val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
        val formattedDate = formatter.format(Date(date))

        if (DateUtils.isToday(date)) {
            return context.getString(R.string.date_s_today, formattedDate)
        } else {
            return formattedDate
        }
    } else {
        "--/--/----"
    }
}
