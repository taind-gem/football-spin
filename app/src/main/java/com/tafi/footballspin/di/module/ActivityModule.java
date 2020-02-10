package com.tafi.footballspin.di.module;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import com.tafi.footballspin.di.ActivityContext;
import com.tafi.footballspin.di.PerActivity;
import com.tafi.footballspin.ui.login.ILoginView;
import com.tafi.footballspin.ui.login.LoginPresenter;
import com.tafi.footballspin.utils.rx.AppSchedulerProvider;
import com.tafi.footballspin.utils.rx.SchedulerProvider;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by taind-201 on 2/7/2020.
 */

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    LoginPresenter<ILoginView> provideLoginPresenter(
            LoginPresenter<ILoginView> presenter) {
        return presenter;
    }

}
