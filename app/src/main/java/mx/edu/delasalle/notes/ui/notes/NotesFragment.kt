package mx.edu.delasalle.notes.ui.notes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.edu.delasalle.notes.R
import mx.edu.delasalle.notes.databinding.FragmentNotesBinding
import mx.edu.delasalle.notes.ui.notes.adapters.NotesAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import mx.edu.delasalle.notes.core.Resource
import mx.edu.delasalle.notes.data.local.AppDatabase
import mx.edu.delasalle.notes.data.local.LocalDataSource
import mx.edu.delasalle.notes.data.remote.ApiClient
import mx.edu.delasalle.notes.data.remote.NoteDataSource
import mx.edu.delasalle.notes.presentation.NoteViewModel
import mx.edu.delasalle.notes.presentation.NoteViewModelFactory
import mx.edu.delasalle.notes.repository.NoteRepositoryImp

class NotesFragment : Fragment(R.layout.fragment_notes) {


    private lateinit var binding: FragmentNotesBinding;
    private lateinit var adapter: NotesAdapter;



    private val viewModel by viewModels<NoteViewModel>() {
        NoteViewModelFactory(NoteRepositoryImp(
            LocalDataSource(AppDatabase.getDatabase(requireContext()).noteDao()),
            NoteDataSource(ApiClient.service)
        ))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNotesBinding.bind(view);
        binding.recyclerNotes.layoutManager = GridLayoutManager(requireContext(),2);


        Log.d("NotesFragment", "onViewCreated: ${viewModel}")

        viewModel.fetchNotes().observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {
                    binding.progressbar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    Log.d("NotesFragment", "Success: ${viewModel}")
                    binding.progressbar.visibility = View.GONE

                    adapter = NotesAdapter( result.data.data){ note ->
                        // onNoteClick(note)
                    }
                    binding.recyclerNotes.adapter = adapter
                     Log.d("LiveData","${result.data.toString()}")
                }
                is Resource.Failure<*> -> {
                    binding.progressbar.visibility = View.GONE
                     Log.d("LiveData","${result.exception.toString()}")
                }
            }
        })

        binding.btnAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_notesFragment_to_noteEditFragment);
        }




    }

    /*
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

     */

}