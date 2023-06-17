package com.example.ntustmovietheater.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ntustmovietheater.model.Movie
import com.example.ntustmovietheater.model.ShowInfo

@Database(entities = [Movie::class, ShowInfo::class], version = 2, exportSchema = false)
abstract class MovieRommDatabase : RoomDatabase() {
    abstract fun movieDao():MovieDao
    abstract fun showInfoDao():ShowInfoDao
    abstract fun movie_showInfoDao():Movie_ShowInfo_Dao
    companion object {
        @Volatile
        private var INSTANCE: MovieRommDatabase? = null
        fun getDatabase(context: Context): MovieRommDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieRommDatabase::class.java,
                    "item_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}