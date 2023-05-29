package com.example.dagger.di

import android.app.Application
import com.example.dagger.MusicPresenter
import dagger.Component

@Component
interface ApplicationComponent {
    fun providePresenter(): MusicPresenter
    
    //либо
  //  fun inject(activity: MusicActivity)
}

class MyApplication: Application() {
  //  val appComponent = DaggerApplicationComponent.create()
}