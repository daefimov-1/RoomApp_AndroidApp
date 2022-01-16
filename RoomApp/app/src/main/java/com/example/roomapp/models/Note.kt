package com.example.roomapp.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int,
    @ColumnInfo(name = "text")
    var text : String,
    @ColumnInfo(name = "timestamp")
    var timestamp : Long,
    @ColumnInfo(name = "done")
    var  done : Boolean
) : Parcelable {
}