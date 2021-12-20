package ru.dromanryuk.notes.feature_note.domain.model

import kotlinx.datetime.*
import kotlinx.datetime.TimeZone
import java.text.SimpleDateFormat
import java.util.*

data class Metadata(
    val creationDateTime: Instant = Clock.System.now(),
    val editingDateTime: Instant = Clock.System.now(),
)

fun Metadata.toEditingTextTime(): String? {
    val yearFormat = SimpleDateFormat(yearPattern, Locale.getDefault())
    val dateFormat = SimpleDateFormat(datePattern, Locale.getDefault())
    val timeFormat = SimpleDateFormat(timePattern, Locale.getDefault())
    val dateTime: LocalDateTime = editingDateTime.toLocalDateTime(TimeZone.UTC)
    val currDate = Clock.System.now().toLocalDateTime(TimeZone.UTC).date

    return when {
        dateTime.date == currDate -> { timeFormat.format(dateTime) }
        dateTime.date < currDate -> { dateFormat.format(dateTime) }
        dateTime.year < currDate.year -> { yearFormat.format(dateTime) }
        else -> null
    }
}

private const val yearPattern = "dd MMMM yyyy"
private const val datePattern = "dd MMMM"
private const val timePattern = "HH:mm"
