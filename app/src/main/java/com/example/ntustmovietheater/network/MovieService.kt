package com.example.ntustmovietheater.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("frontsite/trans/SearchShowAction.do")
    suspend fun getMovie(@Query("method")Method:String,
                            @Query("category")Category:String):List<NetWorkMovie>
}

object MovieNetwork{
    val retrofit = Retrofit.Builder()
        .baseUrl("https://cloud.culture.tw/")
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(
                    KotlinJsonAdapterFactory()
                ).build()))
        .build()
    val movieService = retrofit.create(MovieService::class.java)
}

