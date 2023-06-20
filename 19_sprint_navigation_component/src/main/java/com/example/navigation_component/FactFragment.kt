package com.example.navigation_component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.imdb_api.R
import com.example.imdb_api.databinding.FragmentFactBinding

class FactFragment : Fragment() {
    
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentFactBinding.inflate(layoutInflater)
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val fact = requireArguments().getString(ARGS_FACT) ?: ""
        val imageId = if (fact == getString(R.string.cat_fact)) R.drawable.cat
        else R.drawable.hamster
        
        
        binding.factText.text = fact
        
        binding.btnShowImage.setOnClickListener { findNavController().navigate(
            R.id.action_factFragment_to_imageFragment,
            ImageFragment.createArgs(imageId)
        ) }
        
        binding.navigationToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
    
    companion object {
        private const val ARGS_FACT = "fact"
        
        fun createArgs(fact: String): Bundle = bundleOf(ARGS_FACT to fact)
    }
    
}