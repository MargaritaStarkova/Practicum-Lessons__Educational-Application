package com.example.apiwheather_rxjava

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb_api.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    
    private val forecaBaseUrl = "https://fnw-us.foreca.com"
    
    private var token = ""
    
    private val logging = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    private val okHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(logging)
        .build()
    
    private val retrofit = Retrofit
        .Builder()
        .baseUrl(forecaBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        // Добавляет CallAdapterFactory для RxJava
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
    
    private val forecaService = retrofit.create(ForecaApi::class.java)
    
    private val locations = ArrayList<ForecastLocation>()
    private val adapter = LocationsAdapter {
        showWeather(it)
    }
    
    private lateinit var searchButton: Button
    private lateinit var queryInput: EditText
    private lateinit var placeholderMessage: TextView
    private lateinit var locationsList: RecyclerView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        placeholderMessage = findViewById(R.id.placeholderMessage)
        searchButton = findViewById(R.id.searchButton)
        queryInput = findViewById(R.id.queryInput)
        locationsList = findViewById(R.id.locations)
        
        adapter.locations = locations
        
        locationsList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        locationsList.adapter = adapter
        
        
        searchButton.setOnClickListener {
            if (queryInput.text.isNotEmpty()) {
                if (token.isEmpty()) {
                    searchLocations()
                } else {
                    searchLocations()
                }
            }
        }
    }
    
    private fun showMessage(text: String, additionalMessage: String) {
        if (text.isNotEmpty()) {
            placeholderMessage.visibility = View.VISIBLE
            locations.clear()
            adapter.notifyDataSetChanged()
            placeholderMessage.text = text
            if (additionalMessage.isNotEmpty()) {
                Toast
                    .makeText(applicationContext, additionalMessage, Toast.LENGTH_LONG)
                    .show()
            }
        } else {
            placeholderMessage.visibility = View.GONE
        }
    }
    
    private fun searchLocations() {
        forecaService
            .authenticate(ForecaAuthRequest("margaritabespa", "BtVdsh667xu8"))
            .flatMap { tokenResponse ->
                Log.d("RxJava", "Got access token: $tokenResponse")
                /// Конвертируем полученный accessToken в новый запрос
                token = tokenResponse.token
                Log.d("RxJava", "Got new access token!")
                
                // Переключаемся на следующий сетевой запрос
                val bearerToken = "Bearer ${tokenResponse.token}"
                forecaService.getLocations(bearerToken, queryInput.text.toString())
            }
            .retry { count, throwable ->
                // Описываем логику Retry всей цепочки
                Log.d("RxJava", "Got error! count: $count, $throwable")
                // Если count меньше трёх и мы поймали ошибку 401,
                // тогда перезапускаем всю цепочку
                throwable is HttpException && throwable.code() == 401 && count < 3
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ locationsResponse ->
                // В итоговый subscribe теперь приходят локации
                Log.d("RxJava", "Got locations: ${locationsResponse.locations}")
                locations.clear()
                locations.addAll(locationsResponse.locations)
                adapter.notifyDataSetChanged()
                showMessage("", "")
            },
                
                { error ->
                    
                    Log.e("RxJava", "Got error with auth or locations", error)
                    showMessage(getString(R.string.something_went_wrong), error.toString())
                })
        
    }
    
    private fun showWeather(location: ForecastLocation) {
        forecaService
            .getForecast("Bearer $token", location.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ forecastResponse ->
                val message =
                    "${location.name} t: ${forecastResponse.current.temperature}\n(Ощущается как ${forecastResponse.current.feelsLikeTemp})"
                Toast
                    .makeText(applicationContext, message, Toast.LENGTH_LONG)
                    .show()
                
            },
        
        { error ->
            
            Log.e("RxJava", "Got error with forecastResponse", error)
            Toast
                .makeText(applicationContext, error.localizedMessage, Toast.LENGTH_LONG)
                .show()
        })
    }
}