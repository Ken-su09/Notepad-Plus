package com.suonk.notepad_plus.navigation

import javax.inject.Inject

class NotepadCoordinator @Inject constructor(var navigator: Navigator) {

    fun showSplashScreen() {
        navigator.showSplashScreen()
    }

    fun showMainFragment() {
        navigator.showMainFragment()
    }

    fun showAddNewNote() {
        navigator.showAddNewNote()
    }

    fun showNoteDetails() {
        navigator.showNoteDetails()
    }
}