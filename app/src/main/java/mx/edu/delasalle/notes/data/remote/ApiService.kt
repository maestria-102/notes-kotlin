package mx.edu.delasalle.notes.data.remote

import mx.edu.delasalle.notes.data.model.Note
import mx.edu.delasalle.notes.data.model.NoteList
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {

    @GET("note")
    suspend fun getNotes(): NoteList

    @POST("note")
    suspend fun saveNote(@Body note: Note?): Note?

}