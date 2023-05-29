package com.example.fragment_testing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.practicum.playlistmaker.R
import com.practicum.playlistmaker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // проверяем, если Activity пересоздаётся, то Fragment подгрузится автоматически
        if (savedInstanceState == null) {
            // в этот момент отображаем фрагмент
           /*  supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container_view, CitiesFragment())
                .commit() */
            
            // в этот момент мы показываем Fragment со списком городов
            supportFragmentManager.commit {
                add<CitiesFragment>(R.id.fragment_container_view)
            }
        }
        
        setonClickListeners()
        
    }
    
    private fun setonClickListeners() {
        binding.replaceCountryFragment.setOnClickListener {
            // в этот момент мы заменяем Fragment со списком городов на Fragment со списком стран
            supportFragmentManager.commit {
                replace(R.id.fragment_container_view, CountriesFragment())
                setReorderingAllowed(true)
            }
        }
        binding.replaceBackStackCountryFragment.setOnClickListener {
            // в этот момент мы заменяем Fragment со списком городов на Fragment со списком стран,
            // при этом сохраняя Fragment со списком городов в Back Stack
            supportFragmentManager.commit {
                replace(R.id.fragment_container_view, CountriesFragment())
                addToBackStack("countries")
                setReorderingAllowed(true)
            }
        }
    }
}