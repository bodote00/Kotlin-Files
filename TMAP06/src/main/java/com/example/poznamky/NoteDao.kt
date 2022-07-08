package com.example.poznamky
import androidx.room.*

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    fun getAll () : List <Note>
    @Query("SELECT * FROM notes WHERE archived = 0")
    fun getAllNonArchived (): List <Note>
    @Query("SELECT * FROM notes WHERE id LIKE :id LIMIT 1")
    fun findById ( id : Long): Note
    @Insert
    fun insert (note: Note): Long
    @Delete
    fun delete(note: Note)
    @Update
    fun update(notes: List<Note>) : Int
    @Update
    fun update(note: Note)

}
