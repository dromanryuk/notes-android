package ru.dromanryuk.notes.feature_note.domain.use_case

import android.net.Uri
import ru.dromanryuk.notes.feature_note.domain.repository.NoteShareRepository

class ShareNoteUseCase(
    private val noteShareRepository: NoteShareRepository
) {
    operator fun invoke(content: String, title: String): Uri {
        return noteShareRepository.createShareFile(content, title)
    }
}