package ru.dromanryuk.notes.feature_note.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import ru.dromanryuk.notes.feature_note.domain.filter.NoteFilterState
import ru.dromanryuk.notes.feature_note.domain.repository.NoteFilterRepository
import javax.inject.Inject

class NoteFilterRepositoryImpl @Inject constructor() : NoteFilterRepository {
    private val noteFilterState = MutableStateFlow(NoteFilterState())

    override fun observeNoteFilterState(): Flow<NoteFilterState> = noteFilterState

    override suspend fun updateNoteFilterState(filterState: NoteFilterState) {
        noteFilterState.value = filterState
    }
}