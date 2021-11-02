package com.example.navigation.data.service

import com.example.navigation.data.model.News
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("top-headlines")
    fun getNews(
        @Query("country") country:String,
        @Query("apiKey") apiKey: String
    ): Observable<News>


}