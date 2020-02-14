package com.tafi.footballspin.ui.login

import android.content.Intent
import android.os.Bundle
import com.tafi.footballspin.R
import com.tafi.footballspin.ui.base.BaseActivity
import com.tafi.footballspin.ui.main.MainActivity

class LoginActivity : BaseActivity(), ILoginView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun initView() {}

    override fun openMainActivity() {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
    }
}
