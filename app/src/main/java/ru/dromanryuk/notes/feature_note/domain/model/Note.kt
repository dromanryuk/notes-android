package ru.dromanryuk.notes.feature_note.domain.model

data class Note(
    val id: Int,
    val content: NoteContent,
    val name: String,
    val isFavourite: Boolean,
    val password: Password,
    val metadata: Metadata
)
