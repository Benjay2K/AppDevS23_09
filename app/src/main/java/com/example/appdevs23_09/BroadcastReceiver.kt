package com.example.appdevs23_09

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.util.Log
import android.widget.Toast

// BroadcastReceiver to handle battery-related broadcasts
//using basic information for the lack of BATTERY_STATS permission
class BroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BATTERY_LOW) {
            Toast.makeText(context, "Battery level is low.", Toast.LENGTH_SHORT).show()
        } else if (intent?.action == Intent.ACTION_BATTERY_OKAY) {
            Toast.makeText(context, "Battery level is okay.", Toast.LENGTH_SHORT).show()
        }
    }
}


