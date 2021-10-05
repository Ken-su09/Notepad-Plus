package com.suonk.notepad_plus.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.app.ActivityCompat
import com.suonk.notepad_plus.R
import com.suonk.notepad_plus.databinding.ActivityMainBinding
import com.suonk.notepad_plus.navigation.Navigator
import com.suonk.notepad_plus.navigation.NotepadCoordinator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

    private lateinit var coordinator: NotepadCoordinator
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigator.activity = this
        coordinator = NotepadCoordinator(navigator)

        startSplashScreen()
        Handler(Looper.getMainLooper()).postDelayed({
            startMainFragment()
        }, 5000)
    }

    //region ======================================= Start Fragments ========================================

    private fun startSplashScreen() {
        coordinator.showSplashScreen()
    }

    fun startMainFragment() {
        coordinator.showMainFragment()
    }

    fun showNoteDetails() {
        coordinator.showNoteDetails()
    }

    fun showAddNewNote() {
        coordinator.showAddNewNote()
    }

    //endregion
}