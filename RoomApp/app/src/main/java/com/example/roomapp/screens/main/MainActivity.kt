package com.example.roomapp.screens.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.roomapp.App
import com.example.roomapp.R
import com.example.roomapp.screens.details.NoteDetailsActivity

class MainActivity : AppCompatActivity() {
    private var btnAdd : Button? = null
    private var recyclerView : RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd = findViewById(R.id.btn_create_note)
        btnAdd?.setOnClickListener {
            NoteDetailsActivity.start(this, null)
        }

        recyclerView = findViewById(R.id.rv_notes)
        val adapter = NoteListAdapter(this)
        val mainViewModel : MainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        mainViewModel.getLiveData()?.observe(this, Observer{
            adapter.submitList(it)
        })

        recyclerView?.adapter = adapter
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);

        recyclerView?.layoutManager = staggeredGridLayoutManager

    }


}