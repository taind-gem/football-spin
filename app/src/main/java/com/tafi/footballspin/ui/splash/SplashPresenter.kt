package com.tafi.footballspin.ui.splash

import com.tafi.footballspin.data.DataManager
import com.tafi.footballspin.data.db.model.Player
import com.tafi.footballspin.ui.base.BasePresenter
import com.tafi.footballspin.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SplashPresenter<V : ISplashView> @Inject constructor(
    override var mDataManager: DataManager,
    override var mSchedulerProvider: SchedulerProvider,
    override var mCompositeDisposable: CompositeDisposable
) : BasePresenter<V>(mDataManager, mSchedulerProvider, mCompositeDisposable),
    ISplashPresenter<V> {
    override fun onAttach(view: V) {
        super.onAttach(view)

        mCompositeDisposable.add(
            mDataManager.seedDatabaseTeams()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .switchMap {
                    mDataManager.seedDatabasePlayers()
                }
                .switchMap {
                    mDataManager.getPlayerList()
                }
                .subscribe { players ->
                    mView?.apply {
                        hideLoading()
                        updatePlayerList(players)
                    }
                })

    }

    override fun addPlayer(player: Player) {
        mCompositeDisposable.add(
            mDataManager.insertPlayer(player)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe {
                }
        )
    }

    override fun getPlayerList() {
        mCompositeDisposable.add(
            mDataManager.getPlayerList()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe { players ->
                    mView?.updatePlayerList(players)
                }
        )
    }

}