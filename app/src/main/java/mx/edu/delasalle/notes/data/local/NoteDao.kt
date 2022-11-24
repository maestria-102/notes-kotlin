package mx.edu.delasalle.notes.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mx.edu.delasalle.notes.data.model.NoteEntity

@Dao
interface NoteDao {
    @Query("SELECT * FROM noteEntity")
   suspend fun getNotes():List<NoteEntity>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun saveNote(note:NoteEntity)

}