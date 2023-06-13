package com.example.appdevs23_09

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity(), SensorEventListener {

    //Task 1
    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private lateinit var bubbleLevelView: BubbleLevelView

    //Task 4
    private val broadcastReceiver = BroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bubbleLevelView = findViewById(R.id.bubbleLevelView)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onResume() {
        super.onResume()
        accelerometer?.let {
            // Register the sensor listener
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }

        // Register the broadcast receiver
        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_BATTERY_LOW)
            addAction(Intent.ACTION_BATTERY_OKAY)
        }
        registerReceiver(broadcastReceiver, filter)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)

        // Unregister the broadcast receiver
        unregisterReceiver(broadcastReceiver)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Not used
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            val x = event.values[0] * 100  // Scale the x value
            val y = event.values[1] * 100  // Scale the y value

            // Update the text in the TextView
            val levelTextView: TextView = findViewById(R.id.levelTextView)
            levelTextView.text = "Level: x=$x, y=$y"

            // Update the bubble position
            bubbleLevelView.updateBubblePosition(x, y)
        }
    }
}