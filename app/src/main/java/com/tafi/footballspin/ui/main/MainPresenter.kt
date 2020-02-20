package com.tafi.footballspin.ui.main

import com.tafi.footballspin.data.DataManager
import com.tafi.footballspin.model.League
import com.tafi.footballspin.network.NetworkManager
import com.tafi.footballspin.ui.base.BasePresenter
import com.tafi.footballspin.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import javax.inject.Inject

/**
 * Created by taind-201 on 2/14/2020.
 */

class MainPresenter<V : IMainView> @Inject constructor(
    override var mDataManager: DataManager,
    override var mNetworkManager: NetworkManager,
    override var mSchedulerProvider: SchedulerProvider,
    override var mCompositeDisposable: CompositeDisposable
) : BasePresenter<V>(mDataManager, mNetworkManager, mSchedulerProvider, mCompositeDisposable), IMainPresenter<V> {

    override fun onViewInitialized() {
        mCompositeDisposable.add(
            mDataManager.getAllTeamFromAssets()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(object : Consumer<List<League>> {
                    override fun accept(list: List<League>) {
                        if (!isViewAttached()) {
                            return
                        }

                        mView?.onLoadDataSuccess(list)
                    }
                })
        )
    }

}