package com.example.fragment_testing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.practicum.playlistmaker.databinding.FragmentCitiesBinding

class CitiesFragment : Fragment() {
    
    private val cities = "Yurevichi,Gumist’a,Ptitsefabrika,Orekhovo,Birim,Priiskovyy"
    
    // используем ViewBinding, мы можем использовать его так же как и в Activity
    private var _binding: FragmentCitiesBinding? = null
    
    // создаём неизменяемую переменную, к которой можно будет обращаться без ?.
    // Мы должны не забыть инициализировать _binding, до того как использоватб
    private val binding get() = _binding!!
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        
        _binding = FragmentCitiesBinding.inflate(inflater, container, false)
        
        binding.textView.text = cities
        return binding.root
    }
    
}