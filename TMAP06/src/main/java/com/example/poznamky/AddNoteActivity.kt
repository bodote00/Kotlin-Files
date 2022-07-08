package com.example.poznamky

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class AddNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
    }

    fun addNoteClicked(view: View) {
        intent = Intent()
        intent.putExtra("name", findViewById<EditText>(R.id.addNoteName).text.toString())
        intent.putExtra("text", findViewById<EditText>(R.id.addNoteText).text.toString())
        setResult(RESULT_OK, intent)
        finish()
    }
}