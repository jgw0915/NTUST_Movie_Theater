package com.example.ntustmovietheater.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.ntustmovietheater.model.JoinTable
import com.example.ntustmovietheater.model.Movie
import com.example.ntustmovietheater.model.ShowInfo
import kotlinx.coroutines.flow.Flow

@Dao()
interface Movie_ShowInfo_Dao {
    @Query("SELECT * from Movie "+
            "Inner Join Show_Info ON Movie.uid = Show_Info.movie_uid "+
            " WHERE Movie.uid = :uid")
    suspend fun getMovie(uid: String): JoinTable

}