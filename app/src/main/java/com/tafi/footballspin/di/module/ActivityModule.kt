package com.tafi.footballspin.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.tafi.footballspin.di.scope.ActivityContext
import com.tafi.footballspin.di.scope.PerActivity
import com.tafi.footballspin.ui.login.ILoginPresenter
import com.tafi.footballspin.ui.login.ILoginView
import com.tafi.footballspin.ui.login.LoginPresenter
import com.tafi.footballspin.ui.main.IMainPresenter
import com.tafi.footballspin.ui.main.IMainView
import com.tafi.footballspin.ui.main.MainPresenter
import com.tafi.footballspin.ui.splash.ISplashPresenter
import com.tafi.footballspin.ui.splash.ISplashView
import com.tafi.footballspin.ui.splash.SplashPresenter
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
    fun provideLoginPresenter(presenter: LoginPresenter<ILoginView>): ILoginPresenter<ILoginView> {
        return presenter
    }

    @Provides
    @PerActivity
    fun provideSplashPresenter(presenter: SplashPresenter<ISplashView>): ISplashPresenter<ISplashView> {
        return presenter
    }

    @Provides
    @PerActivity
    fun provideMainPresenter(presenter: MainPresenter<IMainView>): IMainPresenter<IMainView> {
        return presenter
    }

}