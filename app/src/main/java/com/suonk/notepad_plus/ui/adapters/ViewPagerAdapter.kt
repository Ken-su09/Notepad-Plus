package com.suonk.notepad_plus.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.suonk.notepad_plus.ui.fragments.main_pages.AllNotesFragment
import com.suonk.notepad_plus.ui.fragments.main_pages.DeletedNotesFragment
import com.suonk.notepad_plus.ui.fragments.main_pages.FavoriteNotesFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                AllNotesFragment()
            }
            1 -> {
                FavoriteNotesFragment()
            }
            2 -> {
                DeletedNotesFragment()
            }
            else -> {
                AllNotesFragment()
            }
        }
    }
}