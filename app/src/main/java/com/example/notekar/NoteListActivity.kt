package com.example.notekar

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.notekar.databinding.ActivityNoteListBinding


class NoteListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteListBinding.inflate(this.layoutInflater)
        setContentView(binding.root)

        binding.fabAddNotes.setOnClickListener {
            val intent = Intent(
                this@NoteListActivity,
                NoteDetailActivity::class.java
            )
            startActivity(intent)
        }
    }
}

