package ru.dromanryuk.notes.feature_note.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.dromanryuk.notes.feature_note.domain.filter.NoteFilterState

interface NoteFilterRepository {
    fun observeNoteFilterState(): Flow<NoteFilterState>

    suspend fun updateNoteFilterState(filterState: NoteFilterState)
}