package com.example.ntustmovietheater.model

import androidx.room.Embedded
import androidx.room.Relation

data class JoinTable(
    @Embedded
    val movie: Movie,
    @Relation(parentColumn = "uid",
     entityColumn = "movie_uid")
    val showInfo:List<ShowInfo>,
)