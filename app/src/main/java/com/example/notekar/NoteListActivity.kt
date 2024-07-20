package com.example.notekar

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notekar.databinding.ActivityNoteListBinding


class NoteListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteListBinding
    private lateinit var db: DateBaseHelper
    private lateinit var noteListAdapter: NoteListAdapter
    private var mode = Mode.VIEW


    var detailActivityResult = registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (RESULT_OK == result.resultCode) {
            updateNoteList()
        }
    }

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
                val intent = Intent(
                    this@NoteListActivity,
                    NoteDetailActivity::class.java
                )
                intent.putExtra(ID_KEY, noteModel?.id)
                detailActivityResult.launch(intent)
            }
        })

        noteListAdapter.setOnLongClickListener(object :NoteListAdapter. LongNoteClickListener{
            override fun onLongClick(noteModel: NoteModel?) {
                updateMode(Mode.SELECT)
                Toast.makeText(this@NoteListActivity, "clicked", Toast.LENGTH_SHORT).show()
            }
        })


        binding.fabAddNotes.setOnClickListener {
            val intent = Intent(
                this@NoteListActivity,
                NoteDetailActivity::class.java
            )
           detailActivityResult.launch(intent)
        }
    }
    private fun updateMode(mode: Mode) {
        this.mode = mode
        noteListAdapter.setMode(mode)
        invalidateOptionsMenu()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    private fun updateNoteList() {
        val noteList = db.getAll()
        noteListAdapter.setNoteList(noteList)
    }


    enum class Mode {
        SELECT,
        VIEW
    }

}

