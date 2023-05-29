package com.example.sobes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var recyler: RecyclerView

    val baseUrl = "https://api.thecatapi.com/"
    val logging = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    val x = OkHttpClient.Builder().addInterceptor(logging).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(x)
        .build()

    private val catService = retrofit.create(CatApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = ArrayList<CatData>()

        recyler = findViewById(R.id.recycler)
        recyler.adapter = KatAdapter(list)
        recyler.layoutManager = LinearLayoutManager(this)

    catService.getCats().enqueue(object : Callback<Array<CatData>>{

    override fun onResponse(call: Call<Array<CatData>>, response: Response<Array<CatData>>) {

        if (response.code() == 200) {
            list.addAll(response.body()!!)
            recyler.adapter?.notifyDataSetChanged()

        }
        else {
            Toast.makeText(this@MainActivity, "Ничего не найдено", Toast.LENGTH_LONG).show()
        }
    }

    override fun onFailure(call: Call<Array<CatData>>, t: Throwable) {
        Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
    }

})

    }
}