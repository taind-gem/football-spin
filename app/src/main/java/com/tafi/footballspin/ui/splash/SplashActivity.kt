package com.tafi.footballspin.ui.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.tafi.footballspin.BuildConfig
import com.tafi.footballspin.R
import com.tafi.footballspin.data.db.model.Player
import com.tafi.footballspin.recyclerview.PlayerAdapter
import com.tafi.footballspin.recyclerview.devider.VerticalSpaceDecoration
import com.tafi.footballspin.ui.base.BaseActivity
import com.tafi.footballspin.ui.main.MainActivity
import com.tafi.footballspin.ui.main.NewPlayerActivity
import com.tafi.footballspin.utils.AppConstants
import com.tafi.footballspin.utils.CommonUtils
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject

class SplashActivity : BaseActivity(), ISplashView {

    @Inject
    lateinit var mPresenter: SplashPresenter<ISplashView>

    private lateinit var mPlayerAdapter: PlayerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        activityComponent.inject(this)
        mPresenter.onAttach(this)

        initView()
    }

    override fun initView() {

        mPlayerAdapter = PlayerAdapter(this)
        val mLayoutManager = LinearLayoutManager(this)
        rc_player.apply {
            layoutManager = mLayoutManager
            adapter = mPlayerAdapter
            addItemDecoration(VerticalSpaceDecoration(CommonUtils.dpToPx(this@SplashActivity, 12f)))
        }
        mPresenter.getPlayers()

        updateAppVersion()

        //set view event
        btn_add_player.setOnClickListener { openNewPlayerActivity() }
        btn_start.setOnClickListener { openMainActivity() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            AppConstants.REQUEST_CODE_NEW_PLAYER ->
                if (resultCode == Activity.RESULT_OK && intent.hasExtra(AppConstants.EXTRA_NEW_PLAYER)) {
                    val player =
                        intent.getSerializableExtra(AppConstants.EXTRA_NEW_PLAYER) as Player
                    mPresenter.addPlayer(player)
                }

        }
    }

    override fun onDestroy() {
        mPresenter.onDetach()
        super.onDestroy()
    }

    override fun updatePlayerList(listPlayer: List<Player>) {
        val list = arrayListOf(
            Player(1, "dungvv", "Việt Dũng", "dungvv"),
            Player(2, "tuanpb", "Tuan Phan", "tuanpb"),
            Player(3, "taind", "Taihandsome", "taind")
        )
        mPlayerAdapter.mPlayerList = list
    }

    override fun showLoading() {
        sec_loading.visibility = View.VISIBLE
        sec_player.visibility = View.GONE
    }

    override fun hideLoading() {
        sec_loading.visibility = View.GONE
        sec_player.visibility = View.VISIBLE
    }

    override fun updateAppVersion() {
        val version = getString(R.string.version_s, BuildConfig.VERSION_NAME)
        tv_app_version.text = version
    }

    override fun openMainActivity() {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }

    private fun openNewPlayerActivity() {
        val intent = Intent(this, NewPlayerActivity::class.java)
        startActivityForResult(intent, AppConstants.REQUEST_CODE_NEW_PLAYER)
    }

}
