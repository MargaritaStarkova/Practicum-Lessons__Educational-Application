package com.example.broadcastreceiver.sharedprefs

import android.content.Context
import android.content.SharedPreferences

internal class SharedPrefsStorage(context: Context) {

    private val sharedPreferences by lazy {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun incrementSalesCount(lastTransactionId: String) {
        val currentSalesCount = sharedPreferences.getInt(COUNT_TRANSACTIONS_KEY, 0)
        sharedPreferences
            .edit()
            .putInt(COUNT_TRANSACTIONS_KEY, currentSalesCount + 1)
            .putString(LAST_TRANSACTION_ID_KEY, lastTransactionId)
            .apply()
    }

    fun getCurrentSalesData() = SalesData(
        salesCount = getCountTransactions(),
        lastTransactionId = getLastTransactionId()
    )

    private fun getCountTransactions() = sharedPreferences.getInt(COUNT_TRANSACTIONS_KEY, 0)
    private fun getLastTransactionId() = sharedPreferences.getString(LAST_TRANSACTION_ID_KEY, "")

    private companion object {
        const val PREFS_NAME = "sales_prefs"
        const val COUNT_TRANSACTIONS_KEY = "count"
        const val LAST_TRANSACTION_ID_KEY = "last transaction id"
    }
}