package com.yildirim.callmessageapp

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.telephony.SmsManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

fun sendMessage(customerPhone: String,customerMessage: String, context: Context) {
    val formattedPhone = "0$customerPhone"
    try {
        val permission = Manifest.permission.SEND_SMS

        if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(formattedPhone, null, customerMessage, null, null)
            Toast.makeText(context, "Successful Message", Toast.LENGTH_LONG).show()
        } else {
            ActivityCompat.requestPermissions(context as Activity, arrayOf(permission), 0)
        }
    } catch (e: Exception) {
        e.printStackTrace()
        Toast.makeText(context, "Error sending SMS", Toast.LENGTH_LONG).show()
    }
}
