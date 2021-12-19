package ru.dromanryuk.notes.feature_note.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import ru.dromanryuk.notes.feature_note.domain.model.NoteContent

@Entity(
    foreignKeys = [ForeignKey(
        entity = NoteModel::class,
        parentColumns = ["id"],
        childColumns = ["noteId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class NoteTextModel(
    @PrimaryKey
    val noteId: Int,
    val text: String,
)

fun NoteContent.TextNote.toNoteTextModel(noteId: Int) = NoteTextModel(
    text = text,
    noteId = noteId
)
