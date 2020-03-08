package com.tafi.footballspin.ui.main

import com.tafi.footballspin.data.DataManager
import com.tafi.footballspin.ui.base.BasePresenter
import com.tafi.footballspin.ui.base.IView
import com.tafi.footballspin.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by taind-201 on 3/7/2020.
 */

class MainPresenter<V: IView> @Inject constructor(
    override var mDataManager: DataManager,
    override var mSchedulerProvider: SchedulerProvider,
    override var mCompositeDisposable: CompositeDisposable
) : BasePresenter<V>(mDataManager, mSchedulerProvider, mCompositeDisposable) {

}