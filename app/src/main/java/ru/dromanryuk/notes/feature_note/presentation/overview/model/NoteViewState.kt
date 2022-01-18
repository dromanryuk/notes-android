package ru.dromanryuk.notes.feature_note.presentation.overview.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.dromanryuk.notes.feature_note.domain.model.Note
import ru.dromanryuk.notes.feature_note.domain.model.NoteContent
import ru.dromanryuk.notes.feature_note.domain.model.Password
import ru.dromanryuk.notes.feature_note.domain.model.toText

data class NoteViewState(
    val id: Int,
    val title: String,
    val content: String,
    val password: String
)

suspend fun List<Note?>.toNoteViewStates() = withContext(Dispatchers.Default) {
    map { it!!.toNoteViewState() }
}

private fun Note.toNoteViewState() = NoteViewState(
    id = id,
    title = name,
    content = when(content) {
        is NoteContent.TextNote -> content.text
        is NoteContent.ChecklistNote -> content.toText()
    },
    password = when(password) {
        is Password.NonePassword -> ""
        is Password.CodePassword -> password.encryptedPassword
    }
)
