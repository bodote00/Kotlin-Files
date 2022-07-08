package com.example.poznamky

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(@PrimaryKey(autoGenerate = true) val id: Long,
                val title: String,
                val text : String,
                val archived: Boolean) {
}

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity()
{
    var archived: Boolean = false
    var notes = mutableListOf<Note>()
    var recyclerView: RecyclerView? = null
    var adapter: NoteAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        archived = sharedPref.getBoolean("archived", false)

        adapter = NoteAdapter(this)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView = findViewById<RecyclerView>(R.id.recView)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.itemAnimator = DefaultItemAnimator()
        recyclerView?.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        recyclerView?.adapter = adapter

        val dao = AppDb.getDatabase(applicationContext).noteDao()
        if (archived) {
            notes = dao.getAllNonArchived() as MutableList<Note>
        }
        else notes = dao.getAll() as MutableList<Note>
    }

    fun addNote(view: View) {
        val intent = Intent(this, AddNoteActivity::class.java)
        startActivityForResult(intent, 0)
    }

    fun getNotes() {
        val dao = AppDb.getDatabase(applicationContext).noteDao()
        if (archived) {
            notes = dao.getAllNonArchived() as MutableList<Note>
        }
        else notes = dao.getAll() as MutableList<Note>
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==0) {
            val newName = data?.getStringExtra("name")
            val newText = data?.getStringExtra("text")
            if (!newName.isNullOrEmpty() && !newText.isNullOrEmpty()) {
                val newNote: Note = Note(0, newName, newText, true)
                val dao = AppDb.getDatabase(applicationContext).noteDao()
                dao.insert(newNote)
                getNotes()
                adapter?.notifyDataSetChanged()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate (R.menu.activity, menu)
        return true
    }

    override fun onPause() {
        super.onPause()
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit () ) {
            putBoolean("archived", archived )
            apply()
        }
    }

    override fun onResume(){
        super.onResume()
        getNotes()
        adapter?.notifyDataSetChanged()
    }
}