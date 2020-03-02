package com.tafi.footballspin.di.scope

import javax.inject.Qualifier
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * Created by taind-201 on 2/7/2020.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
annotation class ActivityContext
