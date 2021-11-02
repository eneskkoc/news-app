package com.example.navigation.di

import com.example.navigation.ui.fragment.DetailFragment
import com.example.navigation.ui.fragment.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityProvides {
    @ContributesAndroidInjector
    abstract fun bindHomeFragment():MainFragment
    @ContributesAndroidInjector
    abstract fun bindWeeklyFragment():DetailFragment
}