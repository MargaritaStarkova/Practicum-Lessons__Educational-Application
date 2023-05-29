package com.example.kointesting.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Функция, которая настраивает библиотеку Koin, нужно вызвать перед использованием
        startKoin {
            // Метод специального класса, переданного как this, для добавления контекста в граф
            androidContext(this@MyApplication)
            // Передаём все модули, чтобы их содержимое было передано в граф
            modules(repositoryModule, dataModule, viewModelModule)
        }
    }
}