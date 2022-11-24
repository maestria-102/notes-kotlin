package mx.edu.delasalle.notes.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import mx.edu.delasalle.notes.core.Resource
import mx.edu.delasalle.notes.data.model.Note

import mx.edu.delasalle.notes.repository.NoteRepository
import java.security.spec.ECField

class NoteViewModel(private val repository: NoteRepository): ViewModel() {

 fun fetchNotes() = liveData(Dispatchers.IO) {
  emit(Resource.Loading())
     try {
         emit(Resource.Success(repository.getNotes()))
     } catch (exception: Exception) {
         emit(Resource.Failure<Exception>(exception))
         //emit(Resource.Failure(exception))
     }
 }

    fun saveNotes(note: Note?) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repository.saveNote(note)))
        } catch (exception: Exception) {
            Log.e("NoteViewModel", "saveNotes: ${exception.message}")
            emit(Resource.Failure<Exception>(exception))
            //emit(Resource.Failure(exception))
        }
    }

}

class NoteViewModelFactory(private val repository: NoteRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(NoteRepository::class.java).newInstance(repository)
    }
}

