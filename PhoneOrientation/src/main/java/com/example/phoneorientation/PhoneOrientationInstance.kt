package com.example.phoneorientation

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder

// singleton instance in kotlin
object PhoneOrientationInstance {

    fun getInstance(packageCtx: Context, phoneOrientationListener: PhoneOrientationListener) {
        val intent = Intent(packageCtx, PhoneOrientationService::class.java)
        packageCtx.bindService(intent, object : ServiceConnection {
            override fun onServiceDisconnected(p0: ComponentName?) {
            }

            override fun onServiceConnected(p0: ComponentName?, p1: IBinder) {
                PhoneOrientationManager.Stub.asInterface(p1)
                    .measureOrientation(phoneOrientationListener)
            }

        }, Context.BIND_AUTO_CREATE)
    }
}