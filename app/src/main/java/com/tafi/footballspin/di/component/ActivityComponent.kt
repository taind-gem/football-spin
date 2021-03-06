package com.tafi.footballspin.di.component

import com.tafi.footballspin.di.module.ActivityModule
import com.tafi.footballspin.di.scope.PerActivity
import com.tafi.footballspin.ui.login.LoginActivity
import com.tafi.footballspin.ui.main.MainActivity
import com.tafi.footballspin.ui.main.home.HomeFragment
import com.tafi.footballspin.ui.main.result.ResultFragment
import com.tafi.footballspin.ui.splash.SplashActivity
import com.tafi.footballspin.ui.teamselect.TeamSelectActivity
import dagger.Component

/**
 * Created by taind-201 on 2/8/2020.
 */
@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)

    fun inject(activity: SplashActivity)

    fun inject(activity: LoginActivity)

    fun inject(activity: TeamSelectActivity)

    fun inject(fragment: HomeFragment)

    fun inject(fragment: ResultFragment)

}