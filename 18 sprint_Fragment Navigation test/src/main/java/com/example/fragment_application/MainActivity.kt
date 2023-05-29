package com.example.fragment_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.imdb_api.R
import com.example.imdb_api.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add<Authorization>(R.id.fragment_container_view)
            }
        }
    }
}