package com.suonk.notepad_plus.navigation

import androidx.fragment.app.FragmentActivity
import com.suonk.notepad_plus.R
import com.suonk.notepad_plus.ui.fragments.AddNewNoteFragment
import com.suonk.notepad_plus.ui.fragments.NoteDetailsFragment
import com.suonk.notepad_plus.ui.fragments.SplashScreenFragment
import com.suonk.notepad_plus.ui.fragments.main_pages.MainFragment
import javax.inject.Inject

class Navigator @Inject constructor(var activity: FragmentActivity?) {

    fun showSplashScreen() {
        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, SplashScreenFragment())
            .commit()
    }

    fun showMainFragment() {
        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, MainFragment())
            .commit()
    }

    fun showAddNewNote() {
        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, AddNewNoteFragment())
            .addToBackStack(null)
            .commit()
    }

    fun showNoteDetails() {
        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, NoteDetailsFragment())
            .addToBackStack(null)
            .commit()
    }
}