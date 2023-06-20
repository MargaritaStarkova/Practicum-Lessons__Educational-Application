package com.example.navigation_component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.imdb_api.R
import com.example.imdb_api.databinding.FragmentImageBinding

class ImageFragment : Fragment() {
    
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentImageBinding.inflate(layoutInflater)
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
        
        binding.image.setImageResource(requireArguments().getInt(ARGS_IMAGE))
        
        binding.image.animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        binding.image.animate()
        
        binding.btnBackToStart.setOnClickListener {
            findNavController().popBackStack(R.id.startFragment, false)
        }
        
        binding.navigationToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
    
    companion object {
        private const val ARGS_IMAGE = "image"
        
        fun createArgs(imageResId: Int): Bundle = bundleOf(ARGS_IMAGE to imageResId)
    }
    
}