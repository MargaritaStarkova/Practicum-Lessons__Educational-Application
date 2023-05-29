package com.example.sobes

import retrofit2.Call
import retrofit2.http.GET

interface CatApi {
    @GET("v1/images/search?limit=10")
    fun getCats(): Call<Array<CatData>>
}

class ResponseCats (val cats: ArrayList<CatData>)
