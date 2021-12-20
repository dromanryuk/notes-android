package ru.dromanryuk.notes.feature_note.data.repository

import kotlinx.coroutines.flow.Flow
import ru.dromanryuk.notes.feature_note.data.dao.NoteDao
import ru.dromanryuk.notes.feature_note.data.model.NoteModel
import ru.dromanryuk.notes.feature_note.domain.model.Checkbox
import ru.dromanryuk.notes.feature_note.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {
    override fun observeAll(): Flow<List<NoteModel>> {
        return noteDao.getNotes()
    }

    override suspend fun addNote(note: NoteModel) {
        noteDao.insertNote(note)
    }

    override suspend fun addText(text: String) {
        TODO("Not yet implemented")
    }

    override suspend fun addCheckbox(checkbox: Checkbox) {
        TODO("Not yet implemented")
    }

//    override suspend fun addContent(content: NoteContent) {
//        when(content) {
//            is NoteContent.TextNote -> {  }
//            is NoteContent.ChecklistNote -> {  }
//        }
//    }
}