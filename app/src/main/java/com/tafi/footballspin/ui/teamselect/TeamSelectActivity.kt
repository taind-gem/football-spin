package com.tafi.footballspin.ui.teamselect

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.tafi.footballspin.R
import com.tafi.footballspin.data.db.model.Player
import com.tafi.footballspin.data.db.model.Team
import com.tafi.footballspin.recyclerview.TeamSelectAdapter
import com.tafi.footballspin.recyclerview.devider.VerticalSpaceDecoration
import com.tafi.footballspin.ui.base.BaseActivity
import com.tafi.footballspin.utils.AppConstants
import com.tafi.footballspin.utils.CommonUtils
import kotlinx.android.synthetic.main.activity_team_select.*
import javax.inject.Inject

class TeamSelectActivity : BaseActivity(), ITeamSelectView,
    TeamSelectAdapter.OnTeamSelectedListener {

    @Inject
    lateinit var mPresenter: TeamSelectPresenter<ITeamSelectView>

    private lateinit var mTeamAdapter: TeamSelectAdapter

    private lateinit var player: Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_select)

        player = intent.getSerializableExtra(AppConstants.EXTRA_PLAYER) as Player

        activityComponent.inject(this)
        mPresenter.onAttach(this)

        initView()

        mPresenter.getTeams()
    }

    override fun initView() {
        mTeamAdapter = TeamSelectAdapter(this)
        mTeamAdapter.onTeamSelectedListener = this

        val mLayoutManager = LinearLayoutManager(this)
        rc_team_list.apply {
            layoutManager = mLayoutManager
            adapter = mTeamAdapter
            addItemDecoration(
                VerticalSpaceDecoration(
                    CommonUtils.dpToPx(
                        this@TeamSelectActivity,
                        6f
                    )
                )
            )
        }

        img_back.setOnClickListener { finish() }
        tv_select_number.text =
            resources.getString(R.string.selected_number, mTeamAdapter.selectedSet.size)

        btn_finish.setOnClickListener {
            player.listTeam = mTeamAdapter.getSelectedTeams()
            mPresenter.updatePlayer(player)
        }
    }

    override fun onDestroy() {
        mPresenter.onDetach()
        super.onDestroy()
    }

    override fun updatePlayerList(teams: List<Team>) {
        mTeamAdapter.mTeamList = teams
    }

    override fun onTeamSelected() {
        tv_select_number.text =
            resources.getString(R.string.selected_number, mTeamAdapter.selectedSet.size)
    }

}
