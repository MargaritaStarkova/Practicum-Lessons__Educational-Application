package com.example.kointesting

import androidx.lifecycle.ViewModel

class MusicViewModel(
    private val currentTrackId: Int,
    private val repository: MusicRepository,
): ViewModel() {

}