package com.example.poznamky

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class editNoteActivit : AppCompatActivity() {
    lateinit var note: Note
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)
        val id: Long = intent.getLongExtra("id", 0)
        val dao = AppDb.getDatabase(applicationContext).noteDao()

        note = dao.findById(id)

        findViewById<EditText>(R.id.editNoteTitle).setText(note.title)
        findViewById<EditText>(R.id.editNoteText).setText(note.text)
    }

    fun save(view: View)
    {
        val newTitle = findViewById<EditText>(R.id.editNoteTitle).text.toString()
        val newText = findViewById<EditText>(R.id.editNoteText).text.toString()

        if (!newTitle.isNullOrEmpty() && !newText.isNullOrEmpty()) {
            val dao = AppDb.getDatabase(applicationContext).noteDao()

            val newNote: Note = Note(note.id, newTitle, newText, note.archived)
            dao.update(newNote)
            finish()
        }
    }
}