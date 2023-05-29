package com.example.testingretrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface TranslationApi {

    @POST("/translate/yoda.json")
    fun translateToYoda(@Body request: com.example.testingretrofit.TranslationRequest): Call<TranslationResponse>

    @POST("/translate/morse.json")
    fun translateToMorse(@Body request: com.example.testingretrofit.TranslationRequest): Call<TranslationResponse>
}