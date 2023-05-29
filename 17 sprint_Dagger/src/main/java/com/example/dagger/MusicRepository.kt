package com.example.dagger

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MusicRepository @Inject constructor(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource,
) {  }

// Каждый раз создаётся новый экземпляр
class MusicInteractor @Inject constructor(
    private val repository: MusicRepository
) {  }

class RemoteDataSource @Inject constructor() {  }
class LocalDataSource @Inject constructor() {  }