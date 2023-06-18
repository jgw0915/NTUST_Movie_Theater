package com.example.ntustmovietheater.model


data class MovieTicket (
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