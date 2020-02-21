package com.tafi.footballspin.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import com.tafi.footballspin.R
import com.tafi.footballspin.ui.base.BaseActivity
import com.tafi.footballspin.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

/**
 * Created by taind-201 on 2/7/2020.
 */

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
        val view = window.decorView.findViewById<View>(android.R.id.content)
        hideKeyboardWhenClickOutsideEdittext(view)

        edtPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                btnServerLogin.performClick()
                true
            } else {
                false
            }
        }

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
