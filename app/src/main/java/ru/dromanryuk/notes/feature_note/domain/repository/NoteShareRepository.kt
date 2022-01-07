package ru.dromanryuk.notes.feature_note.domain.repository

import android.net.Uri

interface NoteShareRepository {
    fun createShareFile(content: String, title: String): Uri
}