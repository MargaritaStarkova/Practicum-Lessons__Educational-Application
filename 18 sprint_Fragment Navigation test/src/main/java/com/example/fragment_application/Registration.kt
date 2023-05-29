package com.example.fragment_application

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.imdb_api.databinding.Fragment2Binding

class Registration : Fragment() {
    
    private var _binding: Fragment2Binding? = null
    private val binding get() = _binding!!
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = Fragment2Binding.inflate(inflater, container, false)
        
        binding.backBtn.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
        
        return binding.root
        
    }
}
