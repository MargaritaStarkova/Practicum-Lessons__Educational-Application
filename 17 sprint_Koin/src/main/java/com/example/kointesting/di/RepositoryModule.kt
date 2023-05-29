package com.example.kointesting.di

import com.example.kointesting.MusicRepository
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    // Указали, что создаём реализацию интерфейса MusicRepository
    single<MusicRepository> {
        MusicRepository(get(), get())
    } bind MusicRepository::class
    //Также можно явно указать Koin, что метод соответствует определённому типу,
    //с помощью bind (если для одного) или
    // binds (если используется один класс для нескольких классов/интерфейсов).
    
    
}