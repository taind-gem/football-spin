package com.tafi.footballspin.ui.splash

import android.content.Intent
import android.os.Bundle
import com.tafi.footballspin.BuildConfig
import com.tafi.footballspin.R
import com.tafi.footballspin.ui.base.BaseActivity
import com.tafi.footballspin.ui.login.LoginActivity
import com.tafi.footballspin.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject

class SplashActivity : BaseActivity(), ISplashView {

    @Inject
    lateinit var mPresenter: SplashPresenter<ISplashView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        activityComponent.inject(this)

        mPresenter.onAttach(this)

    }

    override fun initView() {
        updateAppVersion()
    }

    override fun onDestroy() {
        mPresenter.onDetach()
        super.onDestroy()
    }

    override fun updateAppVersion() {
        val version = getString(R.string.version_s, BuildConfig.VERSION_NAME)
        tv_app_version.text = version
    }

    override fun openLoginActivity() {
        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        finish()
    }

    override fun openMainActivity() {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }

    override fun startSyncService() {

    }

}
