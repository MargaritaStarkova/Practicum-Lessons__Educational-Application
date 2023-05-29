package com.example.kointesting

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity() {
    

    private var trackId: Int = 0
    
    private val viewModel: MusicViewModel by viewModel {
        parametersOf(trackId)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}