package com.example.audioplayer.model

interface ITrackPlayer {
    //StatusObserver. Реализацию этого интерфейса нужно передать
    //в метод play, чтобы получать обновления прогресса воспроизведения.
    fun play(trackId: Int, statusObserver: StatusObserver)
    fun pause(trackId: Int)
    fun seek(trackId: Int, position: Float)

    fun release(trackId: Int)

    //интерфейс для получения событий, связанных
    // с воспроизведением трека, — обновления прогресса, остановки и старта
    interface StatusObserver{
        fun onProgress(progress: Float)
        fun onStop()
        fun onPlay()
    }
}