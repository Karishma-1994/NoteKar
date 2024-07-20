package com.example.notekar

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notekar.databinding.NoteLayoutBinding

class NoteListAdapter : RecyclerView.Adapter<NoteListAdapter.NoteListViewHolder>() {
    private var noteClickListener: NoteClickListener? = null
    private var longNoteClickListener: LongNoteClickListener? = null
    private var mode = NoteListActivity.Mode.VIEW
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
    fun setOnLongClickListener(longNoteClickListener: LongNoteClickListener?) {
        this.longNoteClickListener = longNoteClickListener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setMode(mode: NoteListActivity.Mode?) {
        this.mode = mode!!
        notifyDataSetChanged()
    }


    inner class NoteListViewHolder(
        private val binding: NoteLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            setClickListener()
            setOnLongClickListener()
        }

        private fun setClickListener() {
            binding.tvLayout.setOnClickListener {
                val position = adapterPosition
                val clickedNoteModel: NoteModel = noteModelList[position]
                noteClickListener?.onClick(clickedNoteModel)
            }
        }

        fun setOnLongClickListener() {
            binding.tvLayout.setOnLongClickListener {
                val position = adapterPosition
                longNoteClickListener!!.onLongClick(noteModelList[position])
                true
            }
        }

        fun bind(noteModel: NoteModel) {
            binding.tvTitle.text = noteModel.title
            binding.tvContent.text = noteModel.content
            if (mode === NoteListActivity.Mode.VIEW) {
                binding.checkbox.visibility = View.GONE
            } else if (mode === NoteListActivity.Mode.SELECT) {
                binding.checkbox.visibility = View.VISIBLE
            }
            binding.checkbox.isChecked = noteModel.isChecked
        }
    }



interface NoteClickListener {
    fun onClick(noteModel: NoteModel?)
}

    interface LongNoteClickListener {
        fun onLongClick(noteModel: NoteModel?)
    }

}

