package com.example.ntustmovietheater.network

import com.example.ntustmovietheater.model.ShowInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class NetworkShowInfo (
//        var movie_id:Int,
        @Json(name="time")
        val startTime:String?,
        @Json(name="locationName")
        val locationName:String?,
        @Json(name="location")
        val location:String?,
        @Json(name="endTime")
        val endTime:String?,
        // onSale's'
        @Json(name="onSales")
        val onSale:String?,
        @Json(name="price")
        val price:String?
    )
