package com.example.ntustmovietheater.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.ntustmovietheater.model.Movie
import com.example.ntustmovietheater.model.ShowInfo
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

@Dao()
interface MovieDao {
    // Specify the conflict strategy as IGNORE, when the user tries to add an
// existing Item into the database.

    @Query("SELECT * FROM Movie")
    suspend fun getAllMovie():List<Movie>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: Movie)

    @Update
    suspend fun update(databaseMovie: Movie)

}