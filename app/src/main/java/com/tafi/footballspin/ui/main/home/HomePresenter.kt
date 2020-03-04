package com.tafi.footballspin.ui.main.home

import com.tafi.footballspin.data.DataManager
import com.tafi.footballspin.data.db.model.Player
import com.tafi.footballspin.data.db.model.Team
import com.tafi.footballspin.data.network.AppNetworkManager
import com.tafi.footballspin.ui.base.BasePresenter
import com.tafi.footballspin.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
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
        var mTeams: List<Team>? = null

        mCompositeDisposable.add(
            mDataManager.getTeamList()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .concatMap { teams ->
                    mTeams = teams
                    return@concatMap mDataManager.getPlayerList()
                }
                .subscribe(Consumer<List<Player>> { players ->
                    for (player in players) {
                        val listTeamId = player.listTeamIdSelected
                        if (!listTeamId.isNullOrEmpty() && mTeams != null) {
                            if (player.listTeam == null) player.listTeam = mutableListOf()
                            for (id in listTeamId) {
                                player.listTeam.plus(mTeams!!.filter { team -> team.id == id })
                            }
                        }
                    }

                    mView?.apply {
                        updateHomeView(players!!)
                        hideLoading()
                    }
                }, Consumer { throwable ->
                    if (!isViewAttached()) {
                        return@Consumer
                    }

                    mView?.apply {
                        onError(throwable.message)
                        hideLoading()
                    }
                }))
    }

}