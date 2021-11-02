package com.example.navigation

import android.content.Context
import androidx.multidex.MultiDex
import dagger.android.AndroidInjector
import com.example.navigation.di.DaggerAppComponent
import dagger.android.DaggerApplication

class NavigationApp: DaggerApplication() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
    override fun applicationInjector(): AndroidInjector<out NavigationApp> {
        return DaggerAppComponent.builder().app(this).build()
    }
}
