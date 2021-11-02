package com.example.navigation.di

import com.example.navigation.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class Bindings {
    @PerActivity
    @ContributesAndroidInjector(modules =[MainActivityProvides::class] )
    abstract fun bindMainActivity(): MainActivity
}