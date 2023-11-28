package com.yildirim.callmessageapp
import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

fun makePhoneCall(customerPhone:String, context: Context) {
    try {
        val formattedPhone = "0$customerPhone"
        val intent = Intent(Intent.ACTION_CALL)
        val phoneUri = Uri.parse("tel:$formattedPhone")
        intent.data = phoneUri

        val permission = Manifest.permission.CALL_PHONE

        if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
            context.startActivity(intent)
        } else {
            ActivityCompat.requestPermissions(context as Activity, arrayOf(permission), 0)
        }
    } catch (e: Exception) {
        e.printStackTrace()
        Toast.makeText(context, "Error making phone call", Toast.LENGTH_LONG).show()
    }
}