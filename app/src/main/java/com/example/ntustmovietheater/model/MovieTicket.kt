package com.example.ntustmovietheater.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "MovieTicket")
data class MovieTicket (
    @PrimaryKey(autoGenerate = false)
    val ticket_id:Int,
    val uid: String,
    val title:String?,
    val startTime:String?,
    val locationName:String?,
    val location:String?,
    val endTime:String?,
    val onSale:String?,
    val price:String?,
    var imageNumber:Int?,
    var people:Int?
        )