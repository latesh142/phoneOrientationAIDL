package com.example.phoneorientation

import android.app.Activity
import android.app.Service
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder

class PhoneOrientationService : Service() {

    lateinit var mSensorManager: SensorManager
    lateinit var mRotationSensor: Sensor

    private val SENSOR_DELAY = 8000


    val binder = object : PhoneOrientationManager.Stub() {
        override fun measureOrientation(listener: PhoneOrientationListener) {
            try {
                mSensorManager = getSystemService(Activity.SENSOR_SERVICE) as SensorManager
                mRotationSensor =
                    mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
                mSensorManager.registerListener(object : SensorEventListener {
                    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
                    }

                    override fun onSensorChanged(p0: SensorEvent) {
                        listener.orientaions(p0.values)
                    }
                }, mRotationSensor, SENSOR_DELAY)
            } catch (e: Exception) {

            }
        }
    }

    override fun onBind(p0: Intent?): IBinder? {
        return binder
    }
}