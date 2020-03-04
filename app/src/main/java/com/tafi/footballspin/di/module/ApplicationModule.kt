package com.tafi.footballspin.di.module

import android.app.Application
import android.content.Context
import com.tafi.footballspin.data.AppDataManager
import com.tafi.footballspin.data.DataManager
import com.tafi.footballspin.data.db.AppDbHelper
import com.tafi.footballspin.data.db.DbHelper
import com.tafi.footballspin.data.network.NetworkManager
import com.tafi.footballspin.data.network.AppNetworkManager
import com.tafi.footballspin.data.prefs.AppPreferencesHelper
import com.tafi.footballspin.data.prefs.PreferencesHelper
import com.tafi.footballspin.di.scope.ApplicationContext
import com.tafi.footballspin.di.scope.DatabaseInfo
import com.tafi.footballspin.di.scope.PreferenceInfo
import com.tafi.footballspin.utils.AppConstants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

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
    @PreferenceInfo
    fun providePreferenceName(): String {
        return AppConstants.PREF_NAME
    }

    @Provides
    @DatabaseInfo
    fun provideDatabaseName(): String {
        return AppConstants.DB_NAME
    }

    @Provides
    @Singleton
    fun provideDbHelper(appDbHelper: AppDbHelper): DbHelper {
        return appDbHelper
    }

    @Provides
    @Singleton
    fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    @Singleton
    fun providePreferencesHelper(appPreferencesHelperHelper: AppPreferencesHelper): PreferencesHelper {
        return appPreferencesHelperHelper
    }

    @Provides
    @Singleton
    fun provideNetworkManager(appNetworkManager: AppNetworkManager): NetworkManager {
        return appNetworkManager
    }
}