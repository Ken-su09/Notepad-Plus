package com.suonk.notepad_plus.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.activityViewModels
import com.suonk.notepad_plus.R
import com.suonk.notepad_plus.databinding.FragmentAddNewNoteBinding
import com.suonk.notepad_plus.databinding.FragmentSplashScreenBinding
import com.suonk.notepad_plus.models.data.Note
import com.suonk.notepad_plus.ui.activity.MainActivity
import com.suonk.notepad_plus.viewmodels.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class AddNewNoteFragment : Fragment() {

    //region ========================================== Val or Var ==========================================

    private var binding: FragmentAddNewNoteBinding? = null

    private lateinit var backButton: AppCompatImageView
    private lateinit var favoriteButton: AppCompatImageView
    private lateinit var unFavoriteButton: AppCompatImageView
    private lateinit var saveButton: AppCompatImageView

    private lateinit var titleEditText: AppCompatEditText
    private lateinit var contentEditText: AppCompatEditText

    private lateinit var dateTextView: AppCompatTextView

    private var date = Calendar.getInstance()
    private var noteDate = ""

    private var isFavorite = 0

    private val viewModel: NoteViewModel by activityViewModels()

    //endregion

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNewNoteBinding.inflate(inflater, container, false)
        initializeUI()
        return binding!!.root
    }

    private fun initializeUI() {
        backButton = binding!!.backIcon
        favoriteButton = binding!!.favoriteIcon
        unFavoriteButton = binding!!.unFavoriteIcon
        saveButton = binding!!.saveIcon
        dateTextView = binding!!.dateTextView

        titleEditText = binding!!.titleEditText
        contentEditText = binding!!.contentEditText

        backButtonClick()
        favoriteClick()
        saveClick()
        getDateToday(date)
    }

    //region ============================================ Clicks ============================================

    private fun backButtonClick() {
        backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun favoriteClick() {
        favoriteButton.setOnClickListener {
            unFavoriteButton.visibility = View.VISIBLE
            favoriteButton.visibility = View.INVISIBLE
            isFavorite = 1
        }

        unFavoriteButton.setOnClickListener {
            unFavoriteButton.visibility = View.GONE
            favoriteButton.visibility = View.VISIBLE
            isFavorite = 0
        }
    }

    private fun saveClick() {
        saveButton.setOnClickListener {
            if (checkIfFieldsEmpty(
                    titleEditText.text.toString(),
                    contentEditText.text.toString()
                )
            ) {
                val bitmap = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.ic_launcher_background,
                    null
                )!!.toBitmap()

                val note = Note(
                    titleEditText.text.toString(),
                    contentEditText.text.toString(),
                    bitmap,
                    getDateToday(date),
                    "",
                    isFavorite,
                    0
                )

                viewModel.createNewNote(note)
                (activity as MainActivity).startMainFragment()
            } else {
                Toast.makeText(requireContext(), "Fields should not be empty", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun getDateToday(date: Calendar): String {
        val rightNow = Calendar.getInstance()
        val hour24 = rightNow.get(Calendar.HOUR_OF_DAY)
        val minutes = rightNow.get(Calendar.MINUTE)

        val hour = if (minutes < 10 && hour24 < 10) {
            "0$hour24:0$minutes"
        } else if (minutes < 10 && hour24 > 10) {
            "$hour24:0$minutes"
        } else if (minutes > 10 && hour24 < 10) {
            "0$hour24:$minutes"
        } else {
            "$hour24:$minutes"
        }

        val day = when {
            date.time.toString().contains("Mon") -> {
                "Lundi"
            }
            date.time.toString().contains("Tue") -> {
                "Mardi"
            }
            date.time.toString().contains("Wed") -> {
                "Mercredi"
            }
            date.time.toString().contains("Thu") -> {
                "Jeudi"
            }
            date.time.toString().contains("Fri") -> {
                "Vendredi"
            }
            date.time.toString().contains("Sat") -> {
                "Samedi"
            }
            date.time.toString().contains("Sun") -> {
                "Dimanche"
            }
            else -> {
                "Lundi"
            }
        }

        val month = when {
            date.time.toString().contains("Jan") -> {
                "Janvier"
            }
            date.time.toString().contains("Feb") -> {
                "Février"
            }
            date.time.toString().contains("Mar") -> {
                "Mars"
            }
            date.time.toString().contains("Apr") -> {
                "Avril"
            }
            date.time.toString().contains("May") -> {
                "Mai"
            }
            date.time.toString().contains("Jun") -> {
                "Juin"
            }
            date.time.toString().contains("Jul") -> {
                "Juillet"
            }
            date.time.toString().contains("Aug") -> {
                "Aout"
            }
            date.time.toString().contains("Sep") -> {
                "Septembre"
            }
            date.time.toString().contains("Oct") -> {
                "Octobre"
            }
            date.time.toString().contains("Nov") -> {
                "Novembre"
            }
            date.time.toString().contains("Dec") -> {
                "Décembre"
            }
            else -> {
                "Avril"
            }
        }

        val year = when {
            date.time.toString().contains("2021") -> {
                "2021"
            }
            date.time.toString().contains("2022") -> {
                "2022"
            }
            else -> {
                "2023"
            }
        }

        dateTextView.text = "$day ${date.time.day} $month $year à $hour"

        return "$year $month $day ${date.time.day} à $hour"
    }

    //endregion

    private fun checkIfFieldsEmpty(title: String, content: String): Boolean {
        return title.isNotEmpty() && content.isNotEmpty()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}