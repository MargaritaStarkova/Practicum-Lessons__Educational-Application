package com.example.testingretrofit

class TranslationResponse(val contents: Content) {

    data class Content(val translated: String,
                       val text: String,
                       val translation: String)
}