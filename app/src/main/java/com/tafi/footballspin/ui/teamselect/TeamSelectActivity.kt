package com.tafi.footballspin.ui.teamselect

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.tafi.footballspin.R
import com.tafi.footballspin.data.db.model.Team
import com.tafi.footballspin.recyclerview.TeamSelectAdapter
import com.tafi.footballspin.recyclerview.devider.VerticalSpaceDecoration
import com.tafi.footballspin.ui.base.BaseActivity
import com.tafi.footballspin.utils.CommonUtils
import kotlinx.android.synthetic.main.activity_team_select.*
import javax.inject.Inject

class TeamSelectActivity : BaseActivity(), ITeamSelectView{

    @Inject
    lateinit var mPresenter: TeamSelectPresenter<ITeamSelectView>

    private lateinit var mTeamAdapter: TeamSelectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_select)
        activityComponent.inject(this)
        mPresenter.onAttach(this)

        initView()

        mPresenter.getTeams()
    }

    override fun initView() {
        mTeamAdapter = TeamSelectAdapter(this)
        val mLayoutManager = LinearLayoutManager(this)
        rc_team_list.apply {
            layoutManager = mLayoutManager
            adapter = mTeamAdapter
            addItemDecoration(VerticalSpaceDecoration(CommonUtils.dpToPx(this@TeamSelectActivity, 6f)))
        }
    }

    override fun onDestroy() {
        mPresenter.onDetach()
        super.onDestroy()
    }

    override fun updatePlayerList(listTeam: List<Team>) {
        mTeamAdapter.mTeamList = listTeam
    }

}
