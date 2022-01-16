package com.example.roomapp.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.roomapp.App
import com.example.roomapp.models.Note

class MainViewModel : ViewModel() {
    private val liveData : LiveData<List<Note?>>? = App.instance.getNoteDao()?.getAllLiveData()

    fun getLiveData() : LiveData<List<Note?>>?{
        return liveData
    }
}