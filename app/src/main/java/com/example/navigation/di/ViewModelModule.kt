package com.example.navigation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.navigation.ui.fragment.DetailFragmentViewModel
import com.example.navigation.ui.fragment.MainFragmentViewModel
import com.example.navigation.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @PerApplication
    abstract fun bindViewModelFactory(factory:ViewModelFactory) : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentViewModel::class)
    abstract fun bindMainFragmentViewModel(viewModel: MainFragmentViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailFragmentViewModel::class)
    abstract fun bindDetailViewModel(viewModel: DetailFragmentViewModel) : ViewModel

}