package com.example.roomapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomapp.models.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    fun getAll(): List<Note?>

    @Query("SELECT * FROM notes")
    fun getAllLiveData(): LiveData<List<Note?>>

    @Query("SELECT * FROM notes WHERE id IN (:notesIds)")
    fun loadAllByIds(notesIds: IntArray): List<Note?>

    @Query("SELECT * FROM notes WHERE id = :findId LIMIT 1")
    fun findById(findId : Int): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note?)

    @Update
    fun update(note : Note?)

    @Delete
    fun delete(note: Note?)
}