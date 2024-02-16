package com.example.toolbar

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null
    private var namesText: TextView? = null
    private var isBackVisible = true
    private val data = listOf("Андрей", "Иван", "Ольга", "Наталья")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        namesText = findViewById(R.id.names_TextView)

        setSupportActionBar(toolbar)//Включение поддержки ActionBar
        //начальное состояние
        supportActionBar?.title = "Заголовок"
        supportActionBar?.subtitle = "Это подзаголовок"
        supportActionBar?.setDisplayHomeAsUpEnabled(isBackVisible)//Включение отображения кнопки назад

    }

    //Инициализация меню работает аналогично ActionBar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        // Обработчик событий для SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            //Функция вызывается при вводе запроса
            override fun onQueryTextSubmit(query: String?) = false

            //Функция вызывается при изменении текста в строке поиска
            override fun onQueryTextChange(newText: String?): Boolean {
                showNames(data.filter { it.contains(newText.toString()) })
                return true
            }

        } )
        return true
    }

    //обработка нажатий в меню
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.title_visibility -> {
                supportActionBar?.title = if (supportActionBar?.title?.isNotEmpty() == true) "" else "Заголовок"
                true
            }

            R.id.subtitle_visibility -> {
                supportActionBar?.subtitle =
                    if (supportActionBar?.subtitle?.isNotEmpty() == true) "" else "Подзаголовок"
                true
            }

            R.id.navigation_visibility -> {
                isBackVisible = !isBackVisible
                supportActionBar?.setDisplayHomeAsUpEnabled(isBackVisible)
                true
            }

            R.id.action_notifications -> {
                Toast.makeText(
                    this, "НОТИФИКАЦИЯ", Toast
                        .LENGTH_SHORT
                ).show()
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun showNames(names: List<String>) {
        namesText?.text = names.joinToString()
    }
}