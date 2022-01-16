package com.example.roomapp.data

import androidx.room.*
import com.example.roomapp.models.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao() : NoteDao

    companion object {
        const val DATABASE_NAME = "Notes.db"
    }
}