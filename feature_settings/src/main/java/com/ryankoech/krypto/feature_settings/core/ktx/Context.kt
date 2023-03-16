package com.ryankoech.krypto.feature_settings.core.ktx

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

fun Context.sendMail(to: String, subject: String, body : String) {
    try {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:") // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, body)
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(this, "No Mail App Found", Toast.LENGTH_SHORT).show()
    } catch (t: Throwable) {
        Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show()
    }
}

fun Context.sendMessage(message : String) {
    try {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, message)
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(this, "Messaging App Found", Toast.LENGTH_SHORT).show()
    } catch (t: Throwable) {
        Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show()
    }
}

fun Context.rateInPlaystore() {
    try {
        val intent = Intent(Intent.ACTION_VIEW,  Uri.parse("market://details?id=${this.packageName}"))
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(this, "PlayStore App Found", Toast.LENGTH_SHORT).show()
    } catch (t: Throwable) {
        Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show()
    }
}