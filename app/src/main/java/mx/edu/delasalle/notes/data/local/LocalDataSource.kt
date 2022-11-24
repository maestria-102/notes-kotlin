package mx.edu.delasalle.notes.data.local

import mx.edu.delasalle.notes.data.model.Note
import mx.edu.delasalle.notes.data.model.NoteList
import mx.edu.delasalle.notes.data.model.toNoteEntity
import mx.edu.delasalle.notes.data.model.toNoteList

class LocalDataSource (private val noteDao: NoteDao) {

    suspend fun getNotes(): NoteList {
        return noteDao.getNotes().toNoteList()
    }

    suspend fun saveNote(note: Note) {
        noteDao.saveNote(note.toNoteEntity())
    }
}
