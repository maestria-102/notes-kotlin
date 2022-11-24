package mx.edu.delasalle.notes.data.remote

import mx.edu.delasalle.notes.data.model.Note
import mx.edu.delasalle.notes.data.model.NoteList

class NoteDataSource (private val apiService: ApiService) {

    suspend fun getNotes(): NoteList = apiService.getNotes()
    suspend fun saveNote(note: Note?): Note? {
        apiService.saveNote(note)
        return note
    }

}