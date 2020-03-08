package com.tafi.footballspin.ui.teamselect

import android.app.Activity
import android.app.Activity.RESULT_OK
import com.tafi.footballspin.data.DataManager
import com.tafi.footballspin.data.db.model.Player
import com.tafi.footballspin.ui.base.BasePresenter
import com.tafi.footballspin.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by taind-201 on 3/3/2020.
 */
class TeamSelectPresenter<V : ITeamSelectView> @Inject constructor(
    override var mDataManager: DataManager,
    override var mSchedulerProvider: SchedulerProvider,
    override var mCompositeDisposable: CompositeDisposable
) : BasePresenter<V>(mDataManager, mSchedulerProvider, mCompositeDisposable),
    ITeamSelectPresenter<V> {

    override fun getTeams() {
        mCompositeDisposable.add(
            mDataManager.getTeamList()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe { listTeam ->
                    mView?.updatePlayerList(listTeam)
                }
        )
    }

    override fun updatePlayer(player: Player) {
        mCompositeDisposable.add(
            mDataManager.updatePlayer(player)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe {
                    (mView as? Activity)?.apply {
                        setResult(RESULT_OK)
                        finish()
                    }
                }
        )
    }

}