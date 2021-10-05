package com.suonk.notepad_plus.ui.fragments.main_pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.suonk.notepad_plus.R
import com.suonk.notepad_plus.databinding.FragmentAllNotesBinding
import com.suonk.notepad_plus.databinding.FragmentMainBinding
import com.suonk.notepad_plus.models.data.Note
import com.suonk.notepad_plus.ui.activity.MainActivity
import com.suonk.notepad_plus.ui.adapters.NotesListAdapter
import com.suonk.notepad_plus.viewmodels.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllNotesFragment : Fragment() {

    //region ========================================== Val or Var ==========================================

    private var binding: FragmentAllNotesBinding? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var notesListAdapter: NotesListAdapter

    private val listOfNotes = mutableListOf<Note>()

    private val viewModel: NoteViewModel by activityViewModels()

    //endregion

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllNotesBinding.inflate(inflater, container, false)
        initializeUI()
        return binding!!.root
    }

    private fun initializeUI() {
        recyclerView = binding!!.allNotesRecyclerView
        notesListAdapter = NotesListAdapter(AllNotesFragment())

        initRecyclerView()
        getNotesFromSearchBar()
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            adapter = notesListAdapter
            getAllNotesFromDatabase()
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }
    }

    fun goToNoteDetails(position: Int) {
        viewModel.apply {
            setNoteLiveData(listOfNotes[position])
            setSearchBarText("")
        }
        (activity as MainActivity).showNoteDetails()
    }

    //region ========================================== ViewModel ==========================================

    private fun getAllNotesFromDatabase() {
        viewModel.allNotes.observe(viewLifecycleOwner, { notes ->
            listOfNotes.clear()
            listOfNotes.addAll(notes)

            notes.let {
                notesListAdapter.submitList(listOfNotes)
            }
        })
    }

    private fun getNotesFromSearchBar() {
        viewModel.allNotes.observe(viewLifecycleOwner, { notes ->
            notes.let {
                viewModel.searchBarText.observe(viewLifecycleOwner, { searchBarText ->
                    if (searchBarText != "" || searchBarText.isEmpty()) {
                        val listOfNotesFilter = mutableListOf<Note>()
                        for (i in notes.indices) {
                            if (notes[i].title.contains(searchBarText) ||
                                notes[i].content.contains(searchBarText) ||
                                notes[i].title.contains(searchBarText.lowercase()) ||
                                notes[i].content.contains(searchBarText.lowercase())
                            ) {
                                listOfNotesFilter.add(notes[i])
                            }
                        }
                        listOfNotes.clear()
                        listOfNotes.addAll(listOfNotesFilter)
                        notesListAdapter.submitList(listOfNotes)
                    }
                })
            }
        })
    }

    //endregion

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}