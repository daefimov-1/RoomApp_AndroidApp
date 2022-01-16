package com.example.roomapp.screens.main

import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.App
import com.example.roomapp.R
import com.example.roomapp.models.Note
import com.example.roomapp.screens.details.NoteDetailsActivity

class NoteViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    private val textView : TextView = itemView.findViewById(R.id.tv_note_text)
    private val btnDelete : ImageButton = itemView.findViewById(R.id.ib_trash)
    private val checkBox : CheckBox = itemView.findViewById(R.id.cb_done)

    private var note : Note? = null
    private var silentChecked = false

    fun bind(note: Note?){
        this.note = note
        textView.text = note?.text ?: "noting (fun bind NoteViewHolder)"
        silentChecked = true
        checkBox.isChecked = note?.done ?: false
        silentChecked = false
    }

    init {
        btnDelete.setOnClickListener {
            App.instance.getNoteDao()?.delete(note)
        }
        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (!silentChecked) {
                note?.done = isChecked
                App.instance.getNoteDao()!!.update(note)
            }
        }
        itemView.setOnClickListener {
            NoteDetailsActivity.start(itemView.context as Activity, note)
        }
    }

}