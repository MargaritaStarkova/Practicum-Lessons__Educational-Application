package com.example.broadcastreceiver

import android.app.Notification.Action
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class AirplaneModeBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
            val isEnabled = intent.getBooleanExtra("state", false)

            Log.d("MyLog", "Airplane mode enabled = $isEnabled")
            Toast.makeText(context, "Airplane mode enabled = $isEnabled", Toast.LENGTH_SHORT).show()
        }
    }
}