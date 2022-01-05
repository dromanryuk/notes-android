package ru.dromanryuk.notes.feature_note.domain.repository

import android.content.Context

interface NoteShareRepository {

    fun shareTextCopy(content: String, context: Context)

    fun sharePdfFile(content: String, title: String, context: Context)

}