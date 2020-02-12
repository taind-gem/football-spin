package com.tafi.footballspin.di.component;

import android.app.Application;
import android.content.Context;
import com.tafi.footballspin.MainApp;
import com.tafi.footballspin.data.DataManager;
import com.tafi.footballspin.di.ApplicationContext;
import com.tafi.footballspin.di.module.ApplicationModule;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Created by taind-201 on 2/7/2020.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MainApp app);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}