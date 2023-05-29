package com.example.kointesting

class LocalDataSource {

}

class RemoteDataSource {

}

class MusicRepository(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource,
) {  }