package com.tafi.footballspin.ui.main.home

import com.tafi.footballspin.R
import com.tafi.footballspin.data.DataManager
import com.tafi.footballspin.data.db.model.Match
import com.tafi.footballspin.data.db.model.Player
import com.tafi.footballspin.data.db.model.Statistic
import com.tafi.footballspin.data.db.model.Team
import com.tafi.footballspin.model.entity.SideType
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
    override var mSchedulerProvider: SchedulerProvider,
    override var mCompositeDisposable: CompositeDisposable
) : BasePresenter<V>(mDataManager, mSchedulerProvider, mCompositeDisposable),
    IHomePresenter<V> {

    var mHostTeam: Team? = null
    var mGuestTeam: Team? = null

    var mHostPlayer: Player? = null
    var mGuestPlayer: Player? = null

    private var listPlayer: List<Player>? = null

    override fun onViewInitialized() {

        mCompositeDisposable.add(
            mDataManager.getPlayerList()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(Consumer<List<Player>> { players ->
                    listPlayer = players

                    mView?.apply {
                        mHostPlayer = listPlayer!![0]
                        mGuestPlayer = listPlayer!![1]

                        mHostTeam = getTeamToPlay(mHostPlayer!!)
                        mGuestTeam = getTeamToPlay(mGuestPlayer!!)


                        updatePlayer(mHostPlayer!!, SideType.HOST)
                        updatePlayer(mGuestPlayer!!, SideType.GUEST)

                        updateTeam(mHostTeam!!, SideType.HOST)
                        updateTeam(mGuestTeam!!, SideType.GUEST)
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
                })
        )
    }

    private fun getTeamToPlay(player: Player): Team? {
        return if (player.listTeam == null) null
        else {
            val team = player.listTeam?.first { team -> !team.isPlayed }
            team ?: player.listTeam!![0]
        }
    }

    override fun saveMatch(createdTime: Long, statistic: Statistic) {
        if (mGuestPlayer == null || mHostPlayer == null) return
        val match = Match(
            hostPlayer = mHostPlayer!!,
            guestPlayer = mGuestPlayer!!,
            hostTeam = mHostTeam!!,
            guestTeam = mGuestTeam!!,
            statistic = statistic,
            createdTime = createdTime,
            drawPoint = mDataManager.getDrawPoint(),
            winPoint = mDataManager.getWinPoint()
        )

        mCompositeDisposable.add(
            mDataManager.saveMatch(match)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe { success ->
                    if (success)
                        mView?.showMessage(R.string.save_match_successful)
                }
        )
    }

    override fun getMatchDetails(): List<Any?> {
        return listOf(mHostPlayer, mGuestPlayer, mHostTeam, mGuestTeam)
    }

}