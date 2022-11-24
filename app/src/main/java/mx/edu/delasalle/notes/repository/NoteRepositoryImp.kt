package mx.edu.delasalle.notes.repository

import mx.edu.delasalle.notes.data.local.LocalDataSource
import mx.edu.delasalle.notes.data.model.Note
import mx.edu.delasalle.notes.data.model.NoteList
import mx.edu.delasalle.notes.data.remote.NoteDataSource


class NoteRepositoryImp (
    private val localDataSource: LocalDataSource,
    private val dataSource: NoteDataSource): NoteRepository {


    override suspend fun getNotes(): NoteList {
        dataSource.getNotes().data.forEach() { note ->
            localDataSource.saveNote(note)
        }
       return dataSource.getNotes()
    }

    override suspend fun saveNote(note: Note?): Note? {

        return dataSource.saveNote(note)
    }
}
