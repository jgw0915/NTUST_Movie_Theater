package com.example.ntustmovietheater

import android.app.Application
import com.example.ntustmovietheater.database.MovieRommDatabase

class MovieApplication : Application(){
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database: MovieRommDatabase by lazy { MovieRommDatabase.getDatabase(this) }
}
