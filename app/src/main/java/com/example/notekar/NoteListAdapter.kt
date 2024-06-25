package com.example.notekar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notekar.databinding.NoteLayoutBinding

class NoteListAdapter : RecyclerView.Adapter<NoteListAdapter.NoteListViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListViewHolder {
        val binding: NoteLayoutBinding =
            NoteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class NoteListViewHolder(
        binding: NoteLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

    }
}