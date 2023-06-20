package com.example.rx_java

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.imdb_api.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.Optional
import java.util.concurrent.TimeUnit
import kotlin.jvm.optionals.getOrNull

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    
        /*         Observable
                    .just(1, 2, 3, 4, 5, 6)
                    .filter { value -> value.mod(2) == 0 }
                    .map { it * it }
                    .subscribe( { value ->
                        Log.d("RxJava", "New value: $value")
                    } ) */
    
        /*         Observable
                    .just(1, 2, 3, 4, 5, 6)
                    .filter { it > 10 }
                    .map { it * it }
                    .subscribe( { value ->
                        Log.d("RxJava", "New value: $value")
                    } ) */
    
        /*         Observable
                    .just(1, 2, 3, 4, 5, 6)
                    .map { it * it }
                    .filter { it > 10 }
                    .subscribe( { value ->
                        Log.d("RxJava", "New value: $value")
                    } ) */
    
        /*         Observable
                    .just(1, 2, 3, 4, 5, 6)
                    .map{ it * it }
                    .filter { if (it < 5) true
                    else throw RuntimeException("Experiment with RxJava")}
                    .subscribe (
                        { value -> Log.d("RxJava", "New value: $value") },
                        { error -> Log.e("RxJava", "onError", error) },
                        { Log.d("RxJava", "onComplete!")}
                    ) */
    
        /*  val intervalObservable = Observable.interval(1, TimeUnit.SECONDS)
         
         val disposable: Disposable = intervalObservable
             .subscribe({ number -> Log.d("RxJava", "onNext: $number") },
                 { error -> Log.e("RxJava", "onError", error) },
                 { Log.d("RxJava", "onComplete!!!") })
         
         
         val handler = Handler(Looper.getMainLooper())
         handler.postDelayed(
             { disposable.dispose() },
             5000L
         )
         handler.postDelayed(
             {
                 val secondDisposable = intervalObservable
                     .subscribe(
                         { number -> Log.d("RxJava", "Second - onNext: $number") },
                         { error -> Log.e("RxJava" , "Second - onError", error) },
                         { Log.d("RxJava", "Second - onComplete")  }
                     )
             },
             10_000L
         ) */
    
        /*         Observable
                    .just(
                        1, 2, 3, 4, 5, 6
                    )
                    .doOnNext { Log.d("RxJava", "doOnNext 1 [after just]: $it") }
                    .doOnError { Log.e("RxJava", "doOnError 1 [after just]", it) }
                    .map { value ->
                        val result = value * value
                        if (result > 10) {
                            throw RuntimeException("Test doOnError!")
                        } else {
                            result
                        }
                    }
                    .doOnNext { Log.d("RxJava", "doOnNext 2 [after map]: $it") }
                    .doOnError { Log.e("RxJava", "doOnError 2 [after map]", it) }
                    .doOnNext { Log.d("RxJava", "doOnNext 3 [after map & doOnError]: $it") }
                    .filter { value ->
                        value > 10
                    }
                    .doOnError { Log.e("RxJava", "doOnError 3 [after filter]", it) }
                    .subscribe({ value -> Log.d("RxJava", "New value: $value") },
                        { error -> Log.e("RxJava", "onError", error) },
                        { Log.d("RxJava", "onComplete!") }) */
    
        //получим NullPointerException
        /*   Observable
              .just(
                  1, 2, 3, 4, 5, 6
              )
              .map { value ->
                  if (value < 4) {
                      value * value
                  } else {
                      null
                  }
              }
              .filter { value ->
                  value > 2
              }
              .subscribe(
                  { value -> Log.d("RxJava", "New value: $value") },
                  { error -> Log.e("RxJava", "onError", error) },
                  { Log.d("RxJava", "onComplete!") }
              ) */
    
        /*  Observable
             .just(1, 2, 3, 4, 5, 6)
             .map {
                 if (it < 4) Optional.of(it * it)
                 else Optional.empty()
             }
             .filter { it.getOrNull() == null || it.get() > 2 }
             .subscribe(
                 { value -> Log.d("RxJava", "New value: ${value.getOrNull()}") },
                 { error -> Log.e("RxJava", "onError", error) },
                 { Log.d("RxJava", "onComplete!") } ) */
    
        /*         Observable
            .just(
                1, 2, 3, 4, 5, 6
            )
            .map { value ->
                val mappedValue = if (value < 4) {
                    value * value
                } else {
                    null
                }
                Optional.ofNullable(mappedValue)
            }
            .filter { it.getOrNull() == null || it.get() > 2 }
            .subscribe(
                { value -> Log.d("RxJava", "New value: ${value.getOrNull()}") },
                { error -> Log.e("RxJava", "onError", error) },
                { Log.d("RxJava", "onComplete!") } ) */
    
        /*         val stringsObservable = Observable.just("1", "2", "three", "4", "five")
            .map { string ->
                try {
                    string.toInt()
                } catch (e: NumberFormatException) {
                    null
                }
            }
            .filter { number ->
                number != null && number % 2 == 0
            }
            .subscribe(
                { number -> Log.d("RxJava", "onNext: $number") },
                { error -> Log.e("RxJava", "onError", error) },
                { Log.d("RxJava", "onComplete") }
            )
     */
     
        val textView = findViewById<TextView>(R.id.textview)
        Observable.interval(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value -> textView.text = "Interval: $value" }
            )
    }
}