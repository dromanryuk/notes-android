package ru.dromanryuk.notes.feature_note.domain.use_case

import android.content.Context
import ru.dromanryuk.notes.feature_note.domain.repository.NoteShareRepository
import ru.dromanryuk.notes.feature_note.presentation.note.model.NoteShareType

class ShareNoteUseCase(
    private val noteShareRepository: NoteShareRepository
) {

    operator fun invoke(shareType: NoteShareType, content: String, title: String, context: Context) {
        when (shareType) {
            NoteShareType.Text -> noteShareRepository.shareTextCopy(content, context)
            NoteShareType.Pdf -> noteShareRepository.sharePdfFile(content, title, context)
        }
    }
}