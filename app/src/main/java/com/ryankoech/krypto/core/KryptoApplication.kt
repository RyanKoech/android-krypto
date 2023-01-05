package com.ryankoech.krypto.core

import android.app.Application
import com.ryankoech.krypto.core.logging.CrashAndLog
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KryptoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        CrashAndLog.setupTimber()
    }

}