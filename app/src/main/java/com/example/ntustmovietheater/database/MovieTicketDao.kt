package com.example.ntustmovietheater.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ntustmovietheater.model.Movie
import com.example.ntustmovietheater.model.MovieTicket

@Dao()
interface MovieTicketDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: MovieTicket)

    @Query("SELECT * FROM MovieTicket")
    suspend fun getAllMovieTicket():MutableList<MovieTicket>

    @Query("DELETE FROM MovieTicket WHERE ticket_id = :id")
    suspend fun deleteTicket(id:Int)
}