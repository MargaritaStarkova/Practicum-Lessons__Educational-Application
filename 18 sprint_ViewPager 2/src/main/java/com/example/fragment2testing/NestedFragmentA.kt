package com.example.fragment2testing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.fragment2testing.databinding.FragmentANestedBinding

// Первый вложенный фрагмент
class NestedFragmentA : BindingFragment<FragmentANestedBinding>() {
    
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentANestedBinding {
        return FragmentANestedBinding.inflate(inflater, container, false)
    }
    
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Установка названия песни - передача данных Activity
        binding.trackName.text = (requireActivity() as SongNameProvider).getSongName()
            .plus(other = " | A")
        
        
        /**
         * При нажатии на кнопку заменяем фрагмент, который находится внутри контейнера
         * "fragment_child_container", на новый
         */
  /*       binding.button.setOnClickListener {
            // Тут транзакция реализована через extension-функцию, а не через цепочку из методов
            parentFragmentManager.commit {
                replace(R.id.fragment_child_container, NestedFragmentB())
                addToBackStack(null)
            }
        } */
        
        // Заставляем наш ViewPager переключиться на следующую страницу
        binding.button.setOnClickListener {
            (parentFragment as? SelectPage)?.navigateTo(page = 1)
        }
        
    }
}
