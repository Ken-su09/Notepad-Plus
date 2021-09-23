package com.suonk.notepad_plus.ui.fragments.main_pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.suonk.notepad_plus.R
import com.suonk.notepad_plus.databinding.FragmentDeletedNotesBinding
import com.suonk.notepad_plus.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeletedNotesFragment : Fragment() {

    private var binding: FragmentDeletedNotesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDeletedNotesBinding.inflate(inflater, container, false)
        initializeUI()
        return binding!!.root
    }

    private fun initializeUI() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}