package com.example.navigation.data

import com.example.navigation.data.model.News
import com.example.navigation.data.service.Api
import com.example.navigation.di.PerApplication
import com.example.navigation.util.Constants
import io.reactivex.Observable
import javax.inject.Inject

@PerApplication
class AppDataManager @Inject constructor(private val mApi: Api) {

    fun api() : Observable<News> {
        return mApi.getNews(Constants.Country,Constants.API_KEY)
    }

}