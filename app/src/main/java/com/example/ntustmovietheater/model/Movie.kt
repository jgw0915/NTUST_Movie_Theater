package com.example.ntustmovietheater.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie")
data class Movie (
        @PrimaryKey(autoGenerate = false)
        val uid: String,
        @ColumnInfo(name = "Title")
        val title:String?,
        @ColumnInfo(name="Description")
        val description:String?,
        @ColumnInfo(name="SourceWeb")
        val promoteUrl:String?
)