package com.tafi.footballspin.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.tafi.footballspin.R
import com.tafi.footballspin.data.db.model.Player
import com.tafi.footballspin.data.db.model.Statistic
import com.tafi.footballspin.data.db.model.Team
import com.tafi.footballspin.model.entity.SideType
import com.tafi.footballspin.ui.base.BaseFragment
import com.tafi.footballspin.ui.view.ResultDialog
import com.tafi.footballspin.utils.CommonUtils
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment(), IHomeView, ResultDialog.OnFinishMatchListener {

    @Inject
    lateinit var presenter: IHomePresenter<IHomeView>

    private var isPlaying = false
    private var createdTime: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        activityComponent?.let { component ->
            component.inject(this)
            presenter.onAttach(this)
        }

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_start_game.setOnClickListener {
            if (isPlaying) {
                showResultDialog()
            } else {
                createdTime = Date().time
            }

            isPlaying = !isPlaying
            changeButtonState(isPlaying)
        }


        showLoading()
        presenter.onViewInitialized()
    }

    private fun changeButtonState(isPlaying: Boolean) {
        btn_start_game.text = resources.getString(
            if (!isPlaying) R.string.start_game
            else R.string.update_result
        )
        btn_select_1.isEnabled = isPlaying != true
        btn_select_2.isEnabled = isPlaying != true
    }


    override fun updatePlayer(player: Player, type: SideType) {
        val res = ContextCompat.getDrawable(activity!!, R.drawable.ic_player_sample)

        when (type) {
            SideType.HOST -> {
                tv_player_name_1.text = player.nickname
                Glide.with(activity!!)
                    .load(CommonUtils.getDrawableByName(activity!!, player.username))
                    .into(img_avatar_1)
                    .onLoadFailed(res)
            }
            SideType.GUEST -> {
                tv_player_name_2.text = player.nickname
                Glide.with(activity!!)
                    .load(CommonUtils.getDrawableByName(activity!!, player.username))
                    .into(img_avatar_2)
                    .onLoadFailed(res)
            }
        }
    }

    override fun updateTeam(team: Team, type: SideType) {
        val res = ContextCompat.getDrawable(activity!!, R.drawable.ic_team_sample)

        when (type) {
            SideType.HOST -> {
                tv_team_name_1.text = team.name
                tv_league_1.text = team.leagueName

                Glide.with(activity!!)
                    .load(CommonUtils.getDrawableByName(activity!!, team.key))
                    .into(img_logo_1)
                    .onLoadFailed(res)
            }
            SideType.GUEST -> {
                tv_team_name_2.text = team.name
                tv_league_2.text = team.leagueName

                Glide.with(activity!!)
                    .load(CommonUtils.getDrawableByName(activity!!, team.key))
                    .into(img_logo_2)
                    .onLoadFailed(res)
            }
        }
    }

    override fun showResultDialog() {
        val detail = presenter.getMatchDetails()
        val dialog = ResultDialog(
            detail[0] as Player,
            detail[1] as Player,
            detail[2] as Team,
            detail[3] as Team
        )
        dialog.onFinishMatchListener = this
        dialog.show(activity!!.supportFragmentManager)
    }

    override fun onFinishMatch(statistic: Statistic) {
        presenter.saveMatch(createdTime, statistic)
    }

}
