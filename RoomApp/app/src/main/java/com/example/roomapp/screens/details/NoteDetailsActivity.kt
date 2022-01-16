package com.example.roomapp.screens.details

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import com.example.roomapp.App
import com.example.roomapp.R
import com.example.roomapp.models.Note

class NoteDetailsActivity : AppCompatActivity() {

    private var note : Note? = null
    private var editText : EditText? = null
    private var backButton : Button?  = null
    private var saveButton : Button?  = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.note_details_page)

        var anim: Animation = AnimationUtils.loadAnimation(this, R.anim.myscale)
        editText = findViewById(R.id.et_note_text)
        setText()

        backButton = findViewById(R.id.btn_back)
        backButton?.setOnClickListener {
            finish()
        }

        saveButton = findViewById(R.id.btn_save_note)
        saveButton?.setOnClickListener{
            saveNote()
        }
        saveButton?.startAnimation(anim)
    }
    private fun setText(){
        if (intent.hasExtra(EXTRA_NOTE)){
            note = intent.getParcelableExtra(EXTRA_NOTE)
            editText?.setText(note?.text)
        }
    }
    private fun saveNote(){
        if (intent.hasExtra(EXTRA_NOTE)){
            note?.text = editText?.text.toString()
            note?.done = false
            note?.timestamp = System.currentTimeMillis()
            App.instance.getNoteDao()?.update(note)

            val listn : List<Note?> = App.instance.getNoteDao()!!.getAll()
            var str : String = "str = ";
            for(note in listn){
                str += note.toString() + "\n"
            }
            Log.v("STR", str)

            finish()
        }
        else{
            note = Note(0, editText?.text.toString(), System.currentTimeMillis(), false)
            App.instance.getNoteDao()?.insert(note)

            val listn : List<Note?> = App.instance.getNoteDao()!!.getAll()
            var str : String = "str = ";
            for(note in listn){
                str += note.toString() + "\n"
            }
            Log.v("STR", str)

            finish()
        }
    }



    companion object{
        private const val EXTRA_NOTE : String = "NoteDetailsActivity.EXTRA_NOTE"
        fun start(caller: Activity, note: Note? ){
            val intent : Intent = Intent(caller, NoteDetailsActivity::class.java)
            if(note != null){
                intent.putExtra(EXTRA_NOTE, note)
            }
            caller.startActivity(intent)
        }
    }
}