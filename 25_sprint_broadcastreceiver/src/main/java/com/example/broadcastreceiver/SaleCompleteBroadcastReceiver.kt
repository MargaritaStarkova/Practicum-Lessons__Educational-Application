package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Process.myPid
import android.util.Log
import com.example.broadcastreceiver.sharedprefs.SharedPrefsStorage

internal class SaleCompleteBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("MyLog", "[processId: ${myPid()}] SalesTransactionsReceiver -> onReceive")
        if (intent?.action != ACTION_SALE_COMPLETE) return

        val transactionId = intent.getStringExtra(EXTRAS_KEY_TRANSACTION_ID)
        if (transactionId != null && context != null) {
            SharedPrefsStorage(context).incrementSalesCount(transactionId)
        }

        Log.d("MyLog", "[processId: ${myPid()}] SalesTransactionsReceiver -> onReceive")

    }

    companion object {
        const val ACTION_SALE_COMPLETE =
            "ru.yandex.practicum.broadcastreceiversexample.sale_complete"
        const val EXTRAS_KEY_TRANSACTION_ID = "transactionId"
    }
}