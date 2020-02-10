package com.tafi.footballspin.di.module

import android.app.Application
import android.content.Context
import com.tafi.footballspin.di.ApplicationContext
import com.tafi.footballspin.di.DatabaseInfo
import com.tafi.footballspin.utils.AppConstants
import dagger.Module
import dagger.Provides

/**
 * Created by taind-201 on 2/7/2020.
 */

@Module
class ApplicationModule(private val mApplication: Application) {
    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return mApplication
    }

    @Provides
    fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @DatabaseInfo
    fun provideDatabaseName(): String {
        return AppConstants.DB_NAME
    }

}