package com.example.notekar

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notekar.databinding.ActivityNoteListBinding


class NoteListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteListBinding
    private lateinit var db: DateBaseHelper
    private lateinit var noteListAdapter: NoteListAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteListBinding.inflate(this.layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar.toolbar)

        supportActionBar?.title = getString(R.string.app_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        db = DateBaseHelper(this)

        noteListAdapter = NoteListAdapter()

        binding.rvNotes.adapter = noteListAdapter
        binding.rvNotes.layoutManager = LinearLayoutManager(this)

        updateNoteList()

        noteListAdapter.setOnClickListener(object : NoteListAdapter.NoteClickListener {
            override fun onClick(noteModel: NoteModel?) {
                println("KarishmaDebug list $noteModel")
                val intent = Intent(
                    this@NoteListActivity,
                    NoteDetailActivity::class.java
                )
                intent.putExtra(ID_KEY, noteModel?.id)
               startActivity(intent)
            }
        })


        binding.fabAddNotes.setOnClickListener {
            val intent = Intent(
                this@NoteListActivity,
                NoteDetailActivity::class.java
            )
           startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    private fun updateNoteList() {
        val noteList = db.getAll()
        noteListAdapter.setNoteList(noteList)
    }
}

