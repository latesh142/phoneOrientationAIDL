package com.example.orientationsensor

import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.phoneorientation.PhoneOrientationInstance
import com.example.phoneorientation.PhoneOrientationListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val coordinates = findViewById<TextView>(R.id.coordinates)

        PhoneOrientationInstance.getInstance(this, object : PhoneOrientationListener {
            override fun orientaions(values: FloatArray) {
                Log.d("Orientation", "" + values)
                var displayValue = ""
                for (value in values) {
                    displayValue += "$value\n"
                }
                runOnUiThread { coordinates.text = displayValue }
            }

            override fun asBinder(): IBinder? {
                return null
            }
        })
    }
}