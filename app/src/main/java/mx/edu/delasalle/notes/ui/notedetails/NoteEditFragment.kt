package mx.edu.delasalle.notes.ui.notedetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import mx.edu.delasalle.notes.R
import mx.edu.delasalle.notes.core.Resource
import mx.edu.delasalle.notes.data.local.AppDatabase
import mx.edu.delasalle.notes.data.local.LocalDataSource
import mx.edu.delasalle.notes.data.model.Note
import mx.edu.delasalle.notes.data.remote.ApiClient
import mx.edu.delasalle.notes.data.remote.NoteDataSource
import mx.edu.delasalle.notes.databinding.FragmentNoteEditBinding
import mx.edu.delasalle.notes.presentation.NoteViewModel
import mx.edu.delasalle.notes.presentation.NoteViewModelFactory
import mx.edu.delasalle.notes.repository.NoteRepositoryImp
import mx.edu.delasalle.notes.ui.notes.adapters.NotesAdapter


class NoteEditFragment : Fragment(R.layout.fragment_note_edit) {


    private lateinit var binding: FragmentNoteEditBinding;

    private val viewModel by viewModels<NoteViewModel>() {
        NoteViewModelFactory(
            NoteRepositoryImp(
            LocalDataSource(AppDatabase.getDatabase(requireContext()).noteDao()),
            NoteDataSource(ApiClient.service)
        )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNoteEditBinding.bind(view);

        binding.btnAddNote.setOnClickListener {
            addNote()
        };
    }


    private fun addNote() {
        val title = binding.editTitle.text.toString()
        val description = binding.editContent.text.toString()
        val url = binding.editImageUrl.text.toString()

        val note = Note(
            title = title,
            content = description,
            image = url
        )
        Log.d("NoteEditFragment", "addNote: $note");
        viewModel.saveNotes(note).observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {
                    binding.progressbar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    Log.d("NotesFragment", "Success: ${viewModel}")
                    binding.progressbar.visibility = View.GONE

                    findNavController().popBackStack()
                    // Add an toast message of success
                    Toast.makeText(requireContext(), "Nota añadida correctamente", Toast.LENGTH_SHORT).show()
                }
                is Resource.Failure<*> -> {
                    binding.progressbar.visibility = View.GONE
                    Log.d("LiveData","${result.exception.toString()}")
                }
            }
        })
    }


    /*
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_edit, container, false)
    }
     */
}