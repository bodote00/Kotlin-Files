package com.example.poznamky

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private val mainActivity: MainActivity) : RecyclerView.Adapter<NoteAdapter.NoteItemHolder> () {

    inner class NoteItemHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        internal var title = view.findViewById<TextView>(R.id.noteTitle)
        internal var body = view.findViewById<TextView>(R.id.noteBody)

        init {
            view.isClickable = true
            view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val intent = Intent(p0?.context, NoteActivity::class.java).apply{
                putExtra("id", mainActivity.notes[adapterPosition].id)
            }
            if (p0 != null) {
                p0.context.startActivity(intent)
                mainActivity.getNotes()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteItemHolder(item)
    }

    override fun onBindViewHolder(holder: NoteItemHolder, pos: Int) {
        val note = mainActivity.notes[pos]
        if (note.archived) {
            holder.title.setTextColor(Color.parseColor("#FFFFFF"))
            holder.body.setTextColor(Color.parseColor("#FFFFFF"))
            holder.itemView.setBackgroundColor(Color.parseColor("#808080"))
        }
        holder.title.text= note.title
        holder.body.text = if (note.text.length < 20) note.text else note.text.substring(0, 20)
    }

    override fun getItemCount(): Int =mainActivity.notes.size

}