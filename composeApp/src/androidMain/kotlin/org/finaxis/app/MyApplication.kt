package org.finaxis.app

import android.app.Application
import app.initializeKoin

class MyApplication : Application() {
    companion object {
        var instance: MyApplication? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initializeKoin()
    }
}