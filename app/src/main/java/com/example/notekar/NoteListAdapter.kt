package com.example.notekar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notekar.databinding.NoteLayoutBinding

class NoteListAdapter : RecyclerView.Adapter<NoteListAdapter.NoteListViewHolder>() {
    private var noteClickListener: NoteClickListener? = null
    private var noteModelList: List<NoteModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListViewHolder {
        val binding: NoteLayoutBinding =
            NoteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return noteModelList.size
    }

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) {
        holder.bind(noteModelList[position])
    }

    fun setNoteList(noteList: List<NoteModel>) {
        noteModelList = noteList
        notifyDataSetChanged()
    }

    fun setOnClickListener(noteClickListener: NoteClickListener) {
        this.noteClickListener = noteClickListener
    }

    inner class NoteListViewHolder(
        private val binding: NoteLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            setClickListener()
        }

        private fun setClickListener() {
            binding.tvLayout.setOnClickListener {
                val position = adapterPosition
                val clickedNoteModel: NoteModel = noteModelList[position]
                noteClickListener?.onClick(clickedNoteModel)
            }
        }

        fun bind(noteModel: NoteModel) {
            binding.tvTitle.text = noteModel.title
            binding.tvContent.text = noteModel.content
        }
    }


    interface NoteClickListener {
        fun onClick(noteModel: NoteModel?)
    }
}
