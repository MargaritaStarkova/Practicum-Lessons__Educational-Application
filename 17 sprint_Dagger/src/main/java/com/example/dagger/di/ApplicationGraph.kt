package com.example.dagger.di

import com.example.dagger.MusicRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface ApplicationGraph {
    fun repository(): MusicRepository
}