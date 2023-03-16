package com.ryankoech.krypto.feature_settings.core.ktx

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

fun Context.sendMail(to: String, subject: String, body : String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("mailto:$to"))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, body)
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(this, "No Mail App Found", Toast.LENGTH_SHORT).show()
    } catch (t: Throwable) {
        Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show()
    }
}