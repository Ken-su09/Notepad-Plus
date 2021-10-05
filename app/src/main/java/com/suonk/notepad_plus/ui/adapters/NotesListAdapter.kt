package com.suonk.notepad_plus.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.suonk.notepad_plus.databinding.ItemNoteBinding
import com.suonk.notepad_plus.models.data.Note
import com.suonk.notepad_plus.ui.fragments.main_pages.AllNotesFragment
import com.suonk.notepad_plus.ui.fragments.main_pages.FavoriteNotesFragment

class NotesListAdapter(private val fragment: Fragment) :
    ListAdapter<Note, NotesListAdapter.ViewHolder>(NotesComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = getItem(position)
        holder.onBind(note, position)
    }

    inner class ViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(note: Note, position: Int) {
            binding.itemNoteTitle.text = note.title
            binding.itemNoteContent.text = note.content
            binding.itemNoteDate.text = note.date

            binding.root.setOnClickListener {
                when (fragment.tag) {
                    "f0" -> (fragment as AllNotesFragment).goToNoteDetails(position)
//                "f1" -> (fragment as FavoriteNotesFragment).goToNoteDetails(position)
                }
            }
        }
    }

    class NotesComparator : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.title == newItem.title &&
                    oldItem.content == newItem.content
        }
    }
}