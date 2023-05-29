package com.example.tablayout_testing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tablayout_testing.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    
    private lateinit var tabMediator: TabLayoutMediator
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.viewPager.adapter = NumbersViewPagerAdapter(supportFragmentManager, lifecycle)
        
        tabMediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
           /*  when(position) {
                0 -> tab.text = "Вкладка 1"
                1 -> tab.text = "Вкладка 2"
                2 -> tab.text = "Вкладка 3"
            } */
            tab.text = "Вкладка ${position + 1}"
        }
        tabMediator.attach()
    }
    
    override fun onDestroy() {
        super.onDestroy()
        tabMediator.detach()
    }
    
}