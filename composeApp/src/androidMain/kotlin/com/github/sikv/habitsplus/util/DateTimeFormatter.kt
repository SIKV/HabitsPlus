package com.github.sikv.habitsplus.util

import android.content.Context
import android.text.format.DateUtils
import com.github.sikv.habitsplus.R
import com.github.sikv.habitsplus.data.model.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateTimeFormatter {

    enum class Pattern(val pattern: String, val checkToday: Boolean = false) {
        MONTH_DAY_YEAR("MM/dd/yyyy", checkToday = true),
        DAY_NUMBER("dd"),
        DAY_NAME("EEEE"),
        FULL_MONTH_NAME("MMMM")
    }

    fun format(context: Context, date: Timestamp?, pattern: Pattern): String {
        return if (date != null) {
            val formatter = SimpleDateFormat(pattern.pattern, Locale.getDefault())
            val formattedDate = formatter.format(Date(date))

            if (pattern.checkToday && DateUtils.isToday(date)) {
                return context.getString(R.string.date_s_today, formattedDate)
            } else {
                return formattedDate
            }
        } else {
            "--/--"
        }
    }
}
