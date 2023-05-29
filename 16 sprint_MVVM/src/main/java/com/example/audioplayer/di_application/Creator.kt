package com.example.audioplayer.di_application

import com.example.audioplayer.model.ITrackInteractor
import com.example.audioplayer.model.ITracksRepository
import com.example.audioplayer.model.NetworkClient
import com.example.audioplayer.model.TracksInteractor
import com.example.audioplayer.model.TracksRepository

object Creator {

    fun provideTracksInteractor(): ITrackInteractor {
        return TracksInteractor(getRepository())
    }

    private fun getRepository(): ITracksRepository {
        return TracksRepository(NetworkClient())
    }
}