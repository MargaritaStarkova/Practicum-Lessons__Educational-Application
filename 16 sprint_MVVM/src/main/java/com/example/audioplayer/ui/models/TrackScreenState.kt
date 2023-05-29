package com.example.audioplayer.ui.models

import com.example.audioplayer.model.TrackModel

sealed class TrackScreenState {
    object Loading: TrackScreenState()
    data class Content(
        val trackModel: TrackModel,
    ): TrackScreenState()
}
