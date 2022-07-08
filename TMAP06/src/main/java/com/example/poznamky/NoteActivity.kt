package com.example.poznamky

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        val id: Long = intent.getLongExtra("id", 0)
        val dao = AppDb.getDatabase(applicationContext).noteDao()

        val note = dao.findById(id)
        findViewById<TextView>(R.id.noteViewName).text = note.title
        findViewById<TextView>(R.id.noteViewText).text = note.text
    }

    fun editNote(view: View) {
        val intent = Intent(this, editNoteActivit::class.java).apply{
            putExtra("id", intent.getLongExtra("id", 0))
        }
        startActivity(intent)
    }

    override fun onResume(){
        super.onResume()
        val id: Long = intent.getLongExtra("id", 0)
        val dao = AppDb.getDatabase(applicationContext).noteDao()

        val note = dao.findById(id)
        findViewById<TextView>(R.id.noteViewName).text = note.title
        findViewById<TextView>(R.id.noteViewText).text = note.text
    }
}