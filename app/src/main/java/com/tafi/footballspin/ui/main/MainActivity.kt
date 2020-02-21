package com.tafi.footballspin.ui.main

import android.os.Bundle
import android.widget.Toast
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
    }

    override fun spinWheel() {
        wheel.rotate(50f, 3000, 50)
    }

    override fun onRotation() {

    }

    override fun onStopRotation(item: Team?) {
        item?.let {
            Toast.makeText(this, it.abbr, Toast.LENGTH_LONG).show()
        }
    }

}
