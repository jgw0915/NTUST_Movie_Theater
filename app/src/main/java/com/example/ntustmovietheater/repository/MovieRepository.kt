package com.example.ntustmovietheater.repository

import android.util.Log
import com.example.ntustmovietheater.network.MovieService
import com.example.ntustmovietheater.database.MovieRoomDatabase
import com.example.ntustmovietheater.network.MovieNetwork
import com.example.ntustmovietheater.network.asDatabaseModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MovieRepository() {

}
