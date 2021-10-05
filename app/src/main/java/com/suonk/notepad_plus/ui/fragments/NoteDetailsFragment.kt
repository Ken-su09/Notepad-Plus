package com.suonk.notepad_plus.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.suonk.notepad_plus.R
import com.suonk.notepad_plus.databinding.FragmentAddNewNoteBinding
import com.suonk.notepad_plus.databinding.FragmentNoteDetailsBinding
import com.suonk.notepad_plus.databinding.FragmentSplashScreenBinding

class NoteDetailsFragment : Fragment() {

    private var binding: FragmentNoteDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteDetailsBinding.inflate(inflater, container, false)
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