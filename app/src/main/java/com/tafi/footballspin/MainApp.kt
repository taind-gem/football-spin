package com.tafi.footballspin

import android.app.Application
import com.tafi.footballspin.di.component.ApplicationComponent
import com.tafi.footballspin.di.component.DaggerApplicationComponent
import com.tafi.footballspin.di.module.ApplicationModule
import com.tafi.footballspin.utils.AppLogger

/**
 * Created by taind-201 on 2/7/2020.
 */

class MainApp : Application() {

    private var mApplicationComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()

        mApplicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this)).build()

        mApplicationComponent!!.inject(this)

        AppLogger.init()
    }

    fun getComponent(): ApplicationComponent? {
        return mApplicationComponent
    }

}