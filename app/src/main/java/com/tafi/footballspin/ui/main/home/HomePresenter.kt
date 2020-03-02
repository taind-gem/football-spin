package com.tafi.footballspin.ui.main.home

import com.tafi.footballspin.data.DataManager
import com.tafi.footballspin.network.AppNetworkManager
import com.tafi.footballspin.ui.base.BasePresenter
import com.tafi.footballspin.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by taind-201 on 2/14/2020.
 */

class HomePresenter<V : IHomeView> @Inject constructor(
    override var mDataManager: DataManager,
    override var mAppNetworkManager: AppNetworkManager,
    override var mSchedulerProvider: SchedulerProvider,
    override var mCompositeDisposable: CompositeDisposable
) : BasePresenter<V>(mDataManager, mAppNetworkManager, mSchedulerProvider, mCompositeDisposable),
    IHomePresenter<V> {

    override fun onViewInitialized() {

    }

}