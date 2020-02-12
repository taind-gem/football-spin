package com.tafi.footballspin.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.tafi.footballspin.di.ActivityContext
import com.tafi.footballspin.di.PerActivity
import com.tafi.footballspin.ui.login.ILoginView
import com.tafi.footballspin.ui.login.LoginPresenter
import com.tafi.footballspin.utils.rx.AppSchedulerProvider
import com.tafi.footballspin.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by taind-201 on 2/7/2020.
 */
@Module
class ActivityModule(private val mActivity: AppCompatActivity) {
    @Provides
    @ActivityContext
    fun provideContext(): Context {
        return mActivity
    }

    @Provides
    fun provideActivity(): AppCompatActivity {
        return mActivity
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

    @Provides
    @PerActivity
    fun provideLoginPresenter(
        presenter: LoginPresenter<ILoginView>
    ): LoginPresenter<ILoginView> {
        return presenter
    }

}