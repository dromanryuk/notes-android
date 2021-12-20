package ru.dromanryuk.notes.feature_note.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.dromanryuk.notes.feature_note.data.dao.NoteDao
import ru.dromanryuk.notes.feature_note.data.db.NoteDatabase
import ru.dromanryuk.notes.feature_note.data.repository.NoteRepositoryImpl
import ru.dromanryuk.notes.feature_note.domain.repository.NoteRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoteDataModel {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): NoteDatabase {
        return Room.databaseBuilder(
            appContext,
            NoteDatabase::class.java,
            NoteDatabase.DB_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideNoteDao(db: NoteDatabase) : NoteDao {
        return db.noteDao()
    }

    @Provides
    fun provideNoteRepository(repository: NoteRepositoryImpl): NoteRepository {
        return repository
    }
}