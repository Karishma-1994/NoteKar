package com.example.notekar

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notekar.databinding.ActivityNoteDetailBinding

const val ID_KEY = "id"

class NoteDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteDetailBinding
    private var viewState = ViewState.CREATE
    private lateinit var db: DateBaseHelper
    private var clickedId = 0
    private var noteModel: NoteModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteDetailBinding.inflate(this.layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar.toolbar)
        db = DateBaseHelper(this)

        clickedId = intent.getIntExtra(ID_KEY, 0)

        if (clickedId > 0) {
            noteModel = db.get(clickedId)
            setDataOnViewFromId()
        }

        supportActionBar?.title = getString(R.string.create_note)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        when (this.viewState) {
            ViewState.VIEW -> {
                menu!!.findItem(R.id.menu_save).setVisible(false)
                menu.findItem(R.id.menu_delete).setVisible(false)
                menu.findItem(R.id.menu_edit).setVisible(true)

            }

            ViewState.EDIT -> {
                menu!!.findItem(R.id.menu_save).setVisible(true)
                menu.findItem(R.id.menu_delete).setVisible(true)
                menu.findItem(R.id.menu_edit).setVisible(false)
            }

            ViewState.CREATE -> {
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
                saveClicked()
                return true
            }

            R.id.menu_edit -> {
                editClicked()
                return true
            }

            R.id.menu_delete -> {
                deleteClicked()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun setDataOnViewFromId() {
        changeViewState(ViewState.VIEW)
        binding.clTextView.root.visibility = View.VISIBLE
        binding.clEditView.root.visibility = View.GONE
        noteModel?.let {
            binding.clTextView.viewTitle.text = it.title
            binding.clTextView.viewContent.text = it.content
        }
    }

    private fun deleteClicked() {
        db.deleteNote((noteModel!!.id))
        Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show()
        returnSuccessResult()
    }

    private fun saveClicked() {
        val title: String = binding.clEditView.editTitle.text.toString().trim()
        val content: String = binding.clEditView.editContent.text.toString().trim()
        if (title.isEmpty() && content.isEmpty()) {
            Toast.makeText(this, "title or detail cannot be empty", Toast.LENGTH_SHORT).show()
        } else {
            if (viewState == ViewState.EDIT && noteModel != null) {
                db.updateNote(title, content, (noteModel!!.id))
            } else {
                db.addNote(title, content)
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
            }
            returnSuccessResult()
        }
    }

    private fun editClicked() {
        changeViewState(ViewState.EDIT)
        binding.clTextView.root.visibility = View.GONE
        binding.clEditView.root.visibility = View.VISIBLE
        binding.clEditView.editTitle.setText(noteModel?.title)
        binding.clEditView.editContent.setText(noteModel?.content)
    }


    private fun changeViewState(viewState: ViewState) {
        this.viewState = viewState
        invalidateOptionsMenu()
    }

    private fun returnSuccessResult() {
        val returnIntent = Intent()
        setResult(RESULT_OK, returnIntent)
        finish()
    }

}

private enum class ViewState {
    CREATE,
    VIEW,
    EDIT
}


