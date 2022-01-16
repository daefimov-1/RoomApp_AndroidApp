package com.example.roomapp

import android.app.Application
import androidx.room.Room
import com.example.roomapp.data.AppDatabase
import com.example.roomapp.data.NoteDao

class App : Application() {
    private var database : AppDatabase? = null
    private var noteDao : NoteDao? = null

    override fun onCreate() {
        super.onCreate()
        instance = this

        database = Room.databaseBuilder(applicationContext,
        AppDatabase::class.java,
        AppDatabase.DATABASE_NAME).allowMainThreadQueries()
            .build()

        noteDao = database!!.noteDao()
    }

    fun getDatabase() : AppDatabase? {
        return database
    }
    fun setDatabase(db : AppDatabase) {
        database = db
    }
    fun getNoteDao() : NoteDao? {
        return noteDao
    }
    fun setNoteDao(nd: NoteDao){
        noteDao = nd
    }
    companion object {
        lateinit var instance: App
            private set
    }
}