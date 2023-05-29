package com.example.fragment2testing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.fragment2testing.databinding.FragmentABinding

// Родительский класс, в который положим вложенные классы NestedFragmentA и NestedFragmentB.
class FragmentA : BindingFragment<FragmentABinding>(), SelectPage {
    
    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentABinding {
        return FragmentABinding.inflate(inflater, container, false)
    }
    
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Установка названия песни и передача данных через аргументы
        binding.trackName.text = requireArguments().getString(TRACK_ID_KEY)
            .plus(other = " | Parent")
    
        // Таким образом происходит установка адаптера нашему ViewPager
        val adapter = PagerAdapter(hostFragment = this)
        binding.fragmentChildContainer.adapter = adapter
    
    
/*         // Добавляем первый вложенный фрагмент
        childFragmentManager.beginTransaction()
            .add(R.id.fragment_child_container, NestedFragmentA())
            .commit() */
    }
    
    
    override fun navigateTo(page: Int) {
        binding.fragmentChildContainer.currentItem = page
    }
    companion object {
        const val TRACK_ID_KEY = "TRACK_ID_KEY"
        
        fun newTrackName(track: String) = FragmentA().apply {
            arguments = bundleOf(TRACK_ID_KEY to track)
        }
    }
}