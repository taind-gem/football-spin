package com.tafi.footballspin.di.component

import com.tafi.footballspin.di.PerActivity
import com.tafi.footballspin.di.module.ActivityModule
import com.tafi.footballspin.ui.main.MainActivity
import com.tafi.footballspin.ui.splash.SplashActivity
import dagger.Component

/**
 * Created by taind-201 on 2/8/2020.
 */
@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity?)

    fun inject(activity: SplashActivity?)

}