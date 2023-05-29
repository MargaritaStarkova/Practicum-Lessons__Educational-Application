package com.practicum.testingviewholder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object{
        val hamsterNames = listOf("Алиса", "Бимбо","Вжик","Дасти", "Китти", "Мафин")

        val hamsterContent = listOf("Говоря об отличии сирийских хомяков от обычных джунгариков, Марина Олеговна отмечает, что особой разницы, кроме размера, нет",
            "Помимо сбалансированного корма, который можно купить в зоомагазине, в рацион следует включать свежую траву, сено, овощи, фрукты.",
            "Регулярно следует чистить вольер, менять подстилку.",
            "Хомяки довольно активные животные. Для того чтобы животным было комфортно, в клетке, вольере следует установить различные приспособления.",
            "Оптимальная для содержания хомяков температура воздуха — 20–24С.",
            "Выпускать хомяков из клетки побегать по комнате можно, но при условии, что животное ручное.")
    }
    private fun getRandomName(): String = hamsterNames[(0..5).random()]

    private fun getRandomContent(): String = hamsterContent[(0..5).random()]

    val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
    recyclerView.layoutManager = LinearLayoutManager(this)

    recycler.adapter = NewsAdapter(
    news = List(100) {
        News(it,getRandomName(),getRandomContent())
    },
    onItemClicked = {

    }
    )


    }
