package ru.dromanryuk.notes.feature_note.domain.use_case

import ru.dromanryuk.notes.feature_note.domain.repository.NoteRepository

class VerificatePasswordUseCase(
    private val noteRepository: NoteRepository,
) {
    suspend operator fun invoke(params: Params): Boolean {
        if (params.password == noteRepository.getPasswordById(params.id)) return true
        return false
    }

    data class Params(
        val id: Int,
        val password: String
    )
}