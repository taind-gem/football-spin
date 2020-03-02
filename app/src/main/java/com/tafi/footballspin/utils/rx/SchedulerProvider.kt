package com.tafi.footballspin.utils.rx

import io.reactivex.Scheduler

/**
 * Created by taind-201 on 2/7/2020.
 */

interface SchedulerProvider {

    fun ui(): Scheduler

    fun computation(): Scheduler

    fun io(): Scheduler

}
