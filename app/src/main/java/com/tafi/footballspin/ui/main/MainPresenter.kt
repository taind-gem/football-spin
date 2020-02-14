package com.tafi.footballspin.ui.main

import com.tafi.footballspin.data.DataManager
import com.tafi.footballspin.ui.base.BasePresenter
import com.tafi.footballspin.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by taind-201 on 2/14/2020.
 */

class MainPresenter<V : IMainView>  @Inject constructor(
    override var mDataManager: DataManager,
    override var mSchedulerProvider: SchedulerProvider,
    override var mCompositeDisposable: CompositeDisposable
) : BasePresenter<V>(mDataManager, mSchedulerProvider, mCompositeDisposable), IMainPresenter<V> {

    override fun onViewInitialized() {

    }

}