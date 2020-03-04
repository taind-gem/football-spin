package com.tafi.footballspin.ui.teamselect

import com.tafi.footballspin.data.DataManager
import com.tafi.footballspin.data.network.AppNetworkManager
import com.tafi.footballspin.ui.base.BasePresenter
import com.tafi.footballspin.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by taind-201 on 3/3/2020.
 */
class TeamSelectPresenter<V : ITeamSelectView> @Inject constructor(
    override var mDataManager: DataManager,
    override var mAppNetworkManager: AppNetworkManager,
    override var mSchedulerProvider: SchedulerProvider,
    override var mCompositeDisposable: CompositeDisposable
) : BasePresenter<V>(mDataManager, mAppNetworkManager, mSchedulerProvider, mCompositeDisposable),
    ITeamSelectPresenter<V> {

    override fun getTeams() {

        mCompositeDisposable.add(
            mDataManager.getTeams()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe { listTeam ->
                    mView?.updatePlayerList(listTeam)
                }
        )
    }

}