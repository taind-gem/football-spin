package com.tafi.footballspin

import android.app.Application
import com.tafi.footballspin.utils.AppLogger

/**
 * Created by taind-201 on 2/7/2020.
 */

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()

        AppLogger.init()
    }
}