package com.example.kointesting.di

import com.example.kointesting.MusicViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { (trackId: Int) ->
        MusicViewModel(trackId, get())
    }
}