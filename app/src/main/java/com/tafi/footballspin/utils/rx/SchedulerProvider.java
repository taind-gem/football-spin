package com.tafi.footballspin.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by taind-201 on 2/7/2020.
 */

public interface SchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();

}
