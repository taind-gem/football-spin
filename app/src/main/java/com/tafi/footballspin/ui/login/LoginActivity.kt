package com.tafi.footballspin.ui.login

import android.content.Intent
import android.os.Bundle
import com.tafi.footballspin.R
import com.tafi.footballspin.ui.base.BaseActivity
import com.tafi.footballspin.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : BaseActivity(), ILoginView {

    @Inject
    lateinit var mPresenter: LoginPresenter<ILoginView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        activityComponent.inject(this)

        mPresenter.onAttach(this)
    }

    override fun initView() {
        btnServerLogin.setOnClickListener {
            mPresenter.onServerLoginClick(
                edtEmail.text.toString(),
                edtPassword.text.toString()
            )
        }
    }

    override fun onDestroy() {
        mPresenter.onDetach()
        super.onDestroy()
    }

    override fun openMainActivity() {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
    }
}
