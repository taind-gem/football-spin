package com.tafi.footballspin.ui.main

import android.os.Bundle
import com.tafi.footballspin.R
import com.tafi.footballspin.model.League
import com.tafi.footballspin.model.Team
import com.tafi.footballspin.ui.base.BaseActivity
import com.tafi.footballspin.view.spinningwheel.SpinningWheel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity(), IMainView, SpinningWheel.OnRotationListener<Team?> {

    @Inject
    lateinit var mPresenter: MainPresenter<IMainView>

    private var currentTeam = 0
    private var isSpinning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityComponent.inject(this)

        mPresenter.onAttach(this)
    }

    override fun initView() {
        mPresenter.onViewInitialized()
    }

    override fun onDestroy() {
        mPresenter.onDetach()
        super.onDestroy()
    }

    override fun onLoadDataSuccess(listLeague: List<League>) {
        val list = mutableListOf<Team>()
        for (league in listLeague) {
            for (club in league.clubs!!) {
                list.add(club)
            }
        }
        wheel.setItems(list)
        wheel.onRotationListener = this

        rotate.setOnClickListener { spinWheel() }

        img_team_logo.setOnClickListener {
            if (!isSpinning) {
                currentTeam = 0
                spinWheel()
            }
        }

        img_team_logo_2.setOnClickListener {
            if (!isSpinning) {
                currentTeam = 1
                spinWheel()
            }
        }
    }

    override fun spinWheel() {
        isSpinning = true
        if (currentTeam == 0) {
            img_arrow_left.setImageResource(R.drawable.ic_arrow_left_active)
            img_arrow_right.setImageResource(R.drawable.ic_arrow_right_disable)
        } else {
            img_arrow_right.setImageResource(R.drawable.ic_arrow_right_active)
            img_arrow_left.setImageResource(R.drawable.ic_arrow_left_disable)
        }
        wheel.rotate(50f, 3000, 50)
    }

    override fun onRotation(item: Team?) {
        item?.let { team ->
            when (currentTeam) {
                0 -> {
                    tv_team_name_1.text = team.name
                    tv_region_1.text = team.league_name

                    val resourceId: Int = resources.getIdentifier(team.key, "drawable", packageName)
                    img_team_logo.setImageResource(resourceId)
                }
                else -> {
                    tv_team_name_2.text = team.name
                    tv_region_2.text = team.league_name

                    val resourceId: Int = resources.getIdentifier(team.key, "drawable", packageName)
                    img_team_logo_2.setImageResource(resourceId)
                }
            }
        }
    }

    override fun onStopRotation(item: Team?) {
        currentTeam = if (currentTeam == 0) {
            img_arrow_right.setImageResource(R.drawable.ic_arrow_right)
            img_arrow_left.setImageResource(R.drawable.ic_arrow_left_disable)
            1
        } else {
            img_arrow_left.setImageResource(R.drawable.ic_arrow_left)
            img_arrow_right.setImageResource(R.drawable.ic_arrow_right_disable)
            0
        }
        isSpinning = false
    }

}
