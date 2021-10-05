package com.suonk.notepad_plus.ui.fragments.main_pages

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayoutMediator
import com.suonk.notepad_plus.R
import com.suonk.notepad_plus.databinding.FragmentMainBinding
import com.suonk.notepad_plus.ui.activity.MainActivity
import com.suonk.notepad_plus.ui.adapters.ViewPagerAdapter
import com.suonk.notepad_plus.viewmodels.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    private lateinit var addNewNoteButton: FloatingActionButton

    private val viewModel: NoteViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        initializeUI()
        return binding!!.root
    }

    private fun initializeUI() {
        addNewNoteButton = binding!!.addNewNoteButton
        setupViewPager()
        searchBarTextListener()
        addNewNoteButtonClick()
    }

    private fun searchBarTextListener() {
        binding!!.searchNoteEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.setSearchBarText(binding!!.searchNoteEditText.text.toString())
            }
        })
    }

    private fun setupViewPager() {
        binding!!.viewPager.adapter = ViewPagerAdapter(activity as MainActivity)

        TabLayoutMediator(
            binding!!.tabLayout,
            binding!!.viewPager
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.icon = ResourcesCompat.getDrawable(resources, R.drawable.ic_notes, null)
                }
                1 -> {
                    tab.icon = ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.ic_star_selected,
                        null
                    )
                }
                2 -> {
                    tab.icon =
                        ResourcesCompat.getDrawable(resources, R.drawable.ic_delete, null)
                }
            }
        }.attach()
    }

    private fun addNewNoteButtonClick() {
        addNewNoteButton.setOnClickListener {
            (activity as MainActivity).showAddNewNote()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}