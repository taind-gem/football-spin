package com.tafi.footballspin

import android.app.Application
import com.tafi.footballspin.data.DataManager
import com.tafi.footballspin.di.component.ApplicationComponent
import com.tafi.footballspin.di.component.DaggerApplicationComponent
import com.tafi.footballspin.di.module.ApplicationModule
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by taind-201 on 2/7/2020.
 */
class MainApp : Application() {

    @Inject
    internal lateinit var mDataManager: DataManager

    private var mApplicationComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()

        mApplicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this)).build()

        mApplicationComponent?.inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    fun getComponent(): ApplicationComponent? {
        return mApplicationComponent
    }

}