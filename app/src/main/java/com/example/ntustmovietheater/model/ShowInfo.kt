package com.example.ntustmovietheater.model

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.ntustmovietheater.model.Movie

@Entity(tableName = "Show_Info",
    foreignKeys = [ForeignKey(
        entity = Movie::class,
        childColumns = ["movie_uid"],
        parentColumns = ["uid"]
    )])
data class ShowInfo (
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        @ColumnInfo(name = "movie_uid")
        val movie_uid:String,
        @ColumnInfo(name="StartTime")
        val startTime:String?,
        @ColumnInfo(name="LocationName")
        val locationName:String?,
        @ColumnInfo(name="Location")
        val location:String?,
        @ColumnInfo(name="EndTime")
        val endTime:String?,
        @Nullable
        @ColumnInfo(name="OnSale")
        val onSale:String?,
        @Nullable
        @ColumnInfo(name="Price")
        val price:String?
    )