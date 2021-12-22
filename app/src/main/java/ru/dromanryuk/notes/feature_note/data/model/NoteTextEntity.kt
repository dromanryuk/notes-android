package ru.dromanryuk.notes.feature_note.data.model

import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.datetime.Instant
import ru.dromanryuk.notes.feature_note.domain.model.Metadata
import ru.dromanryuk.notes.feature_note.domain.model.Note
import ru.dromanryuk.notes.feature_note.domain.model.NoteContent
import ru.dromanryuk.notes.feature_note.domain.model.Password

data class NoteTextEntity(
    @Embedded
    val note: NoteModel,
    @Relation(parentColumn = "id", entityColumn = "noteId")
    val content: NoteTextModel?
)

fun NoteTextEntity.toNote() = Note(
    id = note.id,
    content = NoteContent.TextNote(content!!.text),
    name = note.name,
    isFavourite = note.isFavourite,
    password = Password.NonePassword,
    metadata = Metadata(
        Instant.fromEpochMilliseconds(note.creationTimestamp),
        Instant.fromEpochMilliseconds(note.editingTimestamp)
    )
)
