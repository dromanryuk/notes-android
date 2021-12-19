package ru.dromanryuk.notes.feature_note.data.model

import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.datetime.Instant
import ru.dromanryuk.notes.feature_note.domain.model.*

data class NoteChecklistEntity(
    @Embedded
    val note: NoteModel,
    @Relation(parentColumn = "id", entityColumn = "noteId")
    val checkboxes: List<NoteCheckboxModel>
)

fun NoteChecklistEntity.toNote() = Note(
    id = note.id,
    content = NoteContent.ChecklistNote(checkboxes.map { it.toCheckbox() }),
    name = note.name,
    isFavourite = note.isFavourite,
    password = Password.NonePassword,
    metadata = Metadata(
        Instant.fromEpochMilliseconds(note.creationTimestamp),
        Instant.fromEpochMilliseconds(note.editingTimestamp)
    )
)

fun NoteContent.ChecklistNote.toNoteChecklistEntity(note: Note) = NoteChecklistEntity(
    note = note.toNoteModel(),
    checkboxes = checkboxes.map { it.toNoteCheckboxModel(note.id) }
)
