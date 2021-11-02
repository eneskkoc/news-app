package com.example.navigation.data.service

import com.example.navigation.di.PerApplication
import com.example.navigation.util.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class ApiService {

    @Provides
    @PerApplication
    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }

    @Provides
    @PerApplication
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @PerApplication
    fun provideApiService(gson: Gson, okHttpClient: OkHttpClient): Api {
        return Retrofit.Builder()
            .baseUrl(Constants.API_BASE_PATH)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build().create(Api::class.java)
    }
}