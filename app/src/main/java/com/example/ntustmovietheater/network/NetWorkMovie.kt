package com.example.ntustmovietheater.network

import androidx.room.PrimaryKey
import com.example.ntustmovietheater.model.Movie
import com.example.ntustmovietheater.model.ShowInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class NetWorkMovie(
    @Json(name="UID")
    val uid:String,
    @Json(name="title")
    val title:String?,
    @Json(name="showInfo")
    val networkShowInfo:List<NetworkShowInfo>?,
    @Json(name="descriptionFilterHtml")
    val description:String?,
//    var imageNumber:Int?,
    @Json(name="sourceWebPromote")
    val promoteUrl:String?
)

fun List<NetWorkMovie>.asDatabaseModel(): Array<Pair<Movie, List<ShowInfo>>> {
    return map {
        Pair(
            Movie(
                uid = it.uid,
                title = it.title,
                description = it.description,
                promoteUrl = it.promoteUrl
            ),
            it.networkShowInfo?.map { networkShowInfo ->
                ShowInfo(
                    movie_uid = it.uid,
                    startTime = networkShowInfo.startTime,
                    locationName = networkShowInfo.locationName,
                    location = networkShowInfo.location,
                    endTime = networkShowInfo.endTime,
                    onSale = networkShowInfo.onSale,
                    price = networkShowInfo.price
                )
            } ?: listOf()
        )
    }.toTypedArray()
}