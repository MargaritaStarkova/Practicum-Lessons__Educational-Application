package com.example.audioplayer.model

interface ITrackInteractor {
    fun loadTrackData(onComplete: (TrackModel) -> Unit, trackId: Int)

}
