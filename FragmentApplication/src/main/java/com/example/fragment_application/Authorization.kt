package com.example.fragment_application

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.imdb_api.R
import com.example.imdb_api.databinding.Fragment1Binding

class Authorization : Fragment() {
    
    private var _binding: Fragment1Binding? = null
    private val binding get() = _binding!!
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = Fragment1Binding.inflate(inflater, container, false)
        setOnClickListeners()
        return binding.root
        
    }
    
    private fun setOnClickListeners() {
        binding.registrationBtn.setOnClickListener {
            activity?.supportFragmentManager?.commit {
                replace(R.id.fragment_container_view, Registration())
                addToBackStack("registration")
            }
        }
        
        binding.entryBtn.setOnClickListener {
            activity?.supportFragmentManager?.commit {
                replace(R.id.fragment_container_view, MainScreenApp())
            }
        }
    }
}
