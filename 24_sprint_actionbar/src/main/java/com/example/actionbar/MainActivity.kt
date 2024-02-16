package com.example.actionbar

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView

class MainActivity : AppCompatActivity() {

    private var actionBar: ActionBar? = null
    private var namesText: TextView? = null
    private var isBackVisible = true
    private val data = listOf("Андрей", "Иван", "Ольга", "Наталья")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionBar = supportActionBar
        namesText = findViewById(R.id.names_TextView)

        //начальное состояние
        actionBar?.title = "Заголовок"
        actionBar?.subtitle = "Это подзаголовок"
        actionBar?.setDisplayHomeAsUpEnabled(isBackVisible)
    }

    //создание меню
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
                actionBar?.title = if (actionBar?.title?.isNotEmpty() == true) "" else "Заголовок"
                true
            }

            R.id.subtitle_visibility -> {
                actionBar?.subtitle =
                    if (actionBar?.subtitle?.isNotEmpty() == true) "" else "Подзаголовок"
                true
            }

            R.id.navigation_visibility -> {
                isBackVisible = !isBackVisible
                actionBar?.setDisplayHomeAsUpEnabled(isBackVisible)
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