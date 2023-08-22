package com.example.ntustmovietheater.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ntustmovietheater.model.Movie
import com.example.ntustmovietheater.model.MovieTicket
import com.example.ntustmovietheater.model.ShowInfo

@Database(entities = [Movie::class, ShowInfo::class,MovieTicket::class], version = 4, exportSchema = false)
abstract class MovieRoomDatabase : RoomDatabase() {
    abstract fun movieDao():MovieDao
    abstract fun showInfoDao():ShowInfoDao
    abstract fun movie_showInfoDao():Movie_ShowInfo_Dao

    abstract fun movieTicketDao():MovieTicketDao
    companion object {
        @Volatile
        private var INSTANCE: MovieRoomDatabase? = null
        fun getDatabase(context: Context): MovieRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieRoomDatabase::class.java,
                    "item_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}