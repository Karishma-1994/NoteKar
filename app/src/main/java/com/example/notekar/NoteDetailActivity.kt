package com.example.notekar

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.notekar.databinding.ActivityNoteDetailBinding

class NoteDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteDetailBinding
    private val viewState = ViewState.CREATE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteDetailBinding.inflate(this.layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar.toolbar)

        supportActionBar?.title = getString(R.string.create_note)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        when(this.viewState){
            ViewState.VIEW -> {
                menu!!.findItem(R.id.menu_save).setVisible(false)
                menu.findItem(R.id.menu_delete).setVisible(false)
                menu.findItem(R.id.menu_edit).setVisible(true)

            }
            ViewState.EDIT->{
                menu!!.findItem(R.id.menu_save).setVisible(true)
                menu.findItem(R.id.menu_delete).setVisible(true)
                menu.findItem(R.id.menu_edit).setVisible(false)
            }
            ViewState.CREATE->{
                menu!!.findItem(R.id.menu_save).setVisible(true)
                menu.findItem(R.id.menu_delete).setVisible(false)
                menu.findItem(R.id.menu_edit).setVisible(false)
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }

            R.id.menu_save -> {
            }

            R.id.menu_edit -> {
            }

            R.id.menu_delete -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private enum class ViewState {
        CREATE,
        VIEW,
        EDIT
    }
}

