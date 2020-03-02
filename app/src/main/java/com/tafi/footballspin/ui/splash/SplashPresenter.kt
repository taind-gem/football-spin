package com.tafi.footballspin.ui.splash

import com.tafi.footballspin.data.DataManager
import com.tafi.footballspin.data.db.model.Player
import com.tafi.footballspin.data.db.model.Team
import com.tafi.footballspin.network.AppNetworkManager
import com.tafi.footballspin.ui.base.BasePresenter
import com.tafi.footballspin.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SplashPresenter<V : ISplashView> @Inject constructor(
    override var mDataManager: DataManager,
    override var mAppNetworkManager: AppNetworkManager,
    override var mSchedulerProvider: SchedulerProvider,
    override var mCompositeDisposable: CompositeDisposable
) : BasePresenter<V>(mDataManager, mAppNetworkManager, mSchedulerProvider, mCompositeDisposable),
    ISplashPresenter<V> {

    override fun onAttach(view: V) {
        super.onAttach(view)

        mCompositeDisposable.add(
            mDataManager.readAllTeamFromAsset()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe { list ->
                    if (!isViewAttached()) {
                        return@subscribe
                    }

                        saveTeamList(list)

                }

        )
    }

    private fun saveTeamList(listLeague: List<Team>) {
        mCompositeDisposable.add(
            mDataManager.saveTeamList(listLeague)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe {
                    mView?.hideLoading()
                }
        )
    }

    override fun getPlayers() {
        mCompositeDisposable.add(
            mDataManager.getPlayers()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe { listPlayer ->
                    mView?.updatePlayerList(listPlayer)
                }
        )
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

}