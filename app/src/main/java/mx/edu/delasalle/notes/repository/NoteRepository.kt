package mx.edu.delasalle.notes.repository

import mx.edu.delasalle.notes.data.model.Note
import mx.edu.delasalle.notes.data.model.NoteList

interface NoteRepository {
    suspend fun getNotes(): NoteList
    suspend fun saveNote(note: Note?): Note?
}