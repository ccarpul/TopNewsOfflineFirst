package com.platzi.core.common

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

val apiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")

fun String.getDate(): String {
    val date = LocalDate.parse(this, apiFormat)
    return "${date.month.name.lowercase()
        .replaceFirstChar { 
            it.titlecase(Locale.getDefault())
        }
    } ${date.getDayFormatted()} of ${date.year}"
}


fun LocalDate.getDayFormatted() =
    when (dayOfMonth.toString().last()) {
        '1' -> dayOfMonth.toString() + "st"
        '2' -> dayOfMonth.toString() + "nd"
        '3' -> dayOfMonth.toString() + "rd"
        else -> dayOfMonth.toString() + "th"
    }