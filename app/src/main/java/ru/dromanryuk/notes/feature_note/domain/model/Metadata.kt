package ru.dromanryuk.notes.feature_note.domain.model

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class Metadata(
    val creationDateTime: Instant = Clock.System.now(),
    val editingDateTime: Instant = Clock.System.now(),
)
