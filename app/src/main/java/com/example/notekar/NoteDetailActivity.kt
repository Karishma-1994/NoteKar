package com.example.notekar

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.example.notekar.databinding.ActivityNoteDetailBinding

class NoteDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteDetailBinding.inflate(this.layoutInflater)
        setContentView(binding.root)



    }
    setSupportActionBar(binding.toolbar.toolbar)

    getSupportActionBar().setTitle(getString(R.string.create_note))
    getSupportActionBar().setDisplayHomeAsUpEnabled(true)

}

fun onCreateOptionsMenu(menu: Menu?): Boolean {
    getMenuInflater().inflate(R.menu.detail_menu, menu)
    return true
}


