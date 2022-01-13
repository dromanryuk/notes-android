package ru.dromanryuk.notes.feature_note.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import ru.dromanryuk.notes.feature_note.domain.model.Checkbox

@Entity(
    foreignKeys = [ForeignKey(
        entity = NoteModel::class,
        parentColumns = ["id"],
        childColumns = ["noteId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class NoteCheckboxModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val noteId: Int,
    val content: String,
    val selected: Boolean,
)

fun Checkbox.toNoteCheckboxModel(noteId: Int) = NoteCheckboxModel(
    id = id,
    noteId = noteId,
    selected = selected,
    content = content
)

fun NoteCheckboxModel.toCheckbox() = Checkbox(
    id = id,
    content = content,
    selected = selected
)
