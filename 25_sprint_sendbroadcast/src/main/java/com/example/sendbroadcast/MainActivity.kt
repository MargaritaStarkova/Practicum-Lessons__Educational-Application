package com.example.sendbroadcast

import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.UUID

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.sendBroadcastButton).setOnClickListener {
            sendSaleCompleteBroadcast()
            Log.d("MyLog", "[processId: ${Process.myPid()}] Another App -> Intent was sent!")

        }
    }

    private fun sendSaleCompleteBroadcast() {
        val intent = Intent(BROADCAST_ACTION_SALE_COMPLETE).apply {
            setPackage(TARGET_APP_PACKAGE_NAME)
            putExtra(EXTRAS_KEY_TRANSACTION_ID, generateTransactionId())
        }
        sendBroadcast(intent)
    }

    private fun generateTransactionId(): String {
        return UUID.randomUUID().toString()
    }

    private companion object {
        const val BROADCAST_ACTION_SALE_COMPLETE =
            "ru.yandex.practicum.broadcastreceiversexample.sale_complete"
        const val EXTRAS_KEY_TRANSACTION_ID = "transactionId"
        const val TARGET_APP_PACKAGE_NAME = "com.example.broadcastreceiver"
    }
}