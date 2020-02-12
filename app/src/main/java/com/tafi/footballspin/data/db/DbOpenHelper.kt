package com.tafi.footballspin.data.db

import android.content.Context
import com.tafi.footballspin.data.db.model.DaoMaster.OpenHelper
import com.tafi.footballspin.di.ApplicationContext
import com.tafi.footballspin.di.DatabaseInfo
import org.greenrobot.greendao.database.Database
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by taind-201 on 2/12/2020.
 */
@Singleton
class DbOpenHelper @Inject constructor(@ApplicationContext context: Context?, @DatabaseInfo name: String?) :
    OpenHelper(context, name) {
    override fun onUpgrade(db: Database, oldVersion: Int, newVersion: Int) {
        super.onUpgrade(db, oldVersion, newVersion)
        Timber.d("DB_OLD_VERSION : $oldVersion, DB_NEW_VERSION : $newVersion")
        when (oldVersion) {
            1, 2 -> {
            }
        }
    }
}