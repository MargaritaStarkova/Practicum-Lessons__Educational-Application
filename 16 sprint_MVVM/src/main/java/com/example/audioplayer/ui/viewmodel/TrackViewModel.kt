package com.example.audioplayer.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.audioplayer.di_application.MyApplication
import com.example.audioplayer.model.ITrackInteractor
import com.example.audioplayer.model.ITrackPlayer
import com.example.audioplayer.ui.models.PlayStatus
import com.example.audioplayer.ui.models.TrackScreenState

class TrackViewModel(
    private val trackId: Int,
    private val trackInteractor: ITrackInteractor,
    private val trackPlayer: ITrackPlayer
) : ViewModel() {

    companion object {
        fun getViewModelFactory(trackId: Int): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myApp = this[APPLICATION_KEY] as MyApplication
                val interactor = myApp.provideTracksInteractor()
                val trackPlayer = myApp.provideTrackPlayer()

                TrackViewModel(
                    trackId,
                    interactor,
                    trackPlayer,
                )
            }
        }
    }

    init {
        trackInteractor.loadTrackData(
            trackId = trackId,
            onComplete = { trackModel ->
                screenStateLiveData.postValue(
                    TrackScreenState.Content(trackModel)
                )
            }
        )
    }

    private val screenStateLiveData = MutableLiveData<TrackScreenState>(TrackScreenState.Loading)
    fun getScreenStateLiveData(): LiveData<TrackScreenState> = screenStateLiveData

    private var playStatusLiveData = MutableLiveData<PlayStatus>()
    fun getPlayStatusLiveData(): LiveData<PlayStatus> = playStatusLiveData

    fun play() {
        trackPlayer.play(
            trackId = trackId,
            statusObserver = object : ITrackPlayer.StatusObserver {
                override fun onProgress(progress: Float) {
                    playStatusLiveData.value = getCurrentPlayStatus().copy(progress = progress)
                }

                override fun onStop() {
                    playStatusLiveData.value = getCurrentPlayStatus().copy(isPlaying = false)
                }

                override fun onPlay() {
                    playStatusLiveData.value = getCurrentPlayStatus().copy(isPlaying = true)
                }
            }
        )
    }

    fun pause(){
        trackPlayer.pause(trackId)
    }

    override fun onCleared() {
        super.onCleared()
        trackPlayer.release(trackId)
    }

    private fun getCurrentPlayStatus(): PlayStatus {
        return playStatusLiveData.value ?: PlayStatus(progress = 0f, isPlaying = false)
    }


}