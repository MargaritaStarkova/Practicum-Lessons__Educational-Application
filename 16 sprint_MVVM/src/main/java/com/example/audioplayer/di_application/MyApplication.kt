package com.example.audioplayer.di_application

import android.app.Application
import com.example.audioplayer.model.ITrackInteractor
import com.example.audioplayer.model.ITrackPlayer
import com.example.audioplayer.model.ITracksRepository
import com.example.audioplayer.model.NetworkClient
import com.example.audioplayer.model.TracksInteractor
import com.example.audioplayer.model.TracksRepository

class MyApplication: Application() {

    fun provideTracksInteractor(): ITrackInteractor {
        return TracksInteractor(getRepository())
    }

    private fun getRepository(): ITracksRepository {
        return TracksRepository(NetworkClient())
    }

    fun provideTrackPlayer(): ITrackPlayer {
        TODO("Not yet implemented")
    }
}