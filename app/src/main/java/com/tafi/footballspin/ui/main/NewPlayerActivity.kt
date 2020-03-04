package com.tafi.footballspin.ui.main

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.tafi.footballspin.R
import com.tafi.footballspin.data.db.model.Player
import com.tafi.footballspin.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_new_player.*

class NewPlayerActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_player)
    }

    override fun initView() {
        val view = window.decorView.findViewById<View>(android.R.id.content)
        hideKeyboardWhenClickOutsideEdittext(view)

        btn_finish.setOnClickListener {
            val error = checkInfoValidate()
            if (error != null) {
                showMessage(error)
            } else {
                val player = Player(
                    edt_username.text.toString(),
                    edt_nickname.text.toString(),
                    edt_avatar_url.text.toString()
                )

//                val intent = Intent().putExtra(AppConstants.EXTRA_NEW_PLAYER, player)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }

    private fun checkInfoValidate(): String? {

        if (edt_username.text.toString() == ""
            || edt_nickname.text.toString() == ""
            || edt_avatar_url.text.toString() == ""
        )
            return resources.getString(R.string.error_input_all)
        return null
    }

}
