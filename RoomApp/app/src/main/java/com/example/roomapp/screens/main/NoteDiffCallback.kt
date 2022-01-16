package com.example.roomapp.screens.main

import androidx.recyclerview.widget.DiffUtil
import com.example.roomapp.models.Note

class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.text == newItem.text
    }
}