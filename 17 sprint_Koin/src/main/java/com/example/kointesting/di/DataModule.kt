package com.example.kointesting.di

import com.example.kointesting.LocalDataSource
import com.example.kointesting.RemoteDataSource
import org.koin.dsl.module

val dataModule = module {
    single {
        LocalDataSource()
    }
    
    single {
        RemoteDataSource()
    }
}