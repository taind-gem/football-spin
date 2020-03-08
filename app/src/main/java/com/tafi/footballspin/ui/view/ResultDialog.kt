package com.tafi.footballspin.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.tafi.footballspin.R
import com.tafi.footballspin.data.db.model.Player
import com.tafi.footballspin.data.db.model.Statistic
import com.tafi.footballspin.data.db.model.Team
import com.tafi.footballspin.ui.base.BaseDialog
import com.tafi.footballspin.utils.CommonUtils
import kotlinx.android.synthetic.main.dialog_update_result.*

/**
 * Created by taind-201 on 3/8/2020.
 */
class ResultDialog(
    var player_1: Player,
    var player_2: Player,
    var team_1: Team,
    var team_2: Team
) : BaseDialog() {

    var onFinishMatchListener: OnFinishMatchListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_update_result, container, false)
    }

    override fun setUp(view: View) {
        tv_player_name_1.text = player_1.nickname
        tv_player_name_2.text = player_2.nickname

        val res = ContextCompat.getDrawable(context!!, R.drawable.ic_team_sample)
        Glide.with(context!!)
            .load(CommonUtils.getDrawableByName(context!!, team_1.key))
            .into(img_logo_1)
            .onLoadFailed(res)

        Glide.with(context!!)
            .load(CommonUtils.getDrawableByName(context!!, team_2.key))
            .into(img_logo_2)
            .onLoadFailed(res)

        chk_red_1.setOnClickListener { if (chk_red_1.isChecked) chk_red_2.isChecked = false }
        chk_red_2.setOnClickListener { if (chk_red_2.isChecked) chk_red_1.isChecked = false }

        btn_update.setOnClickListener {
            val error = validateData()
            if (error != null) {
                showMessage(error)
                return@setOnClickListener
            }

            val statistic = Statistic()
            statistic.apply {
                hostScore = edt_score_1.text.toString().toLong()
                guestScore = edt_score_2.text.toString().toLong()
                red = when {
                    chk_red_1.isChecked -> 1
                    chk_red_2.isChecked -> -1
                    else -> 0
                }
                yellow = when {
                    chk_yellow_1.isChecked -> 1
                    chk_yellow_2.isChecked -> -1
                    else -> 0
                }
            }
            onFinishMatchListener?.onFinishMatch(statistic)
            dismiss()
        }

        btn_cancel.setOnClickListener { dismiss() }
    }

    private fun validateData(): String? {
        var error: String? = null
        if (edt_score_1.text.toString() == "" || edt_score_2.text.toString() == "")
            error = context?.resources?.getString(R.string.please_update_score)
        return error
    }

    fun show(fragmentManager: FragmentManager) {
        super.show(fragmentManager, TAG)
    }

    companion object {
        const val TAG = "RateUsDialog"
    }

    interface OnFinishMatchListener {
        fun onFinishMatch(statistic: Statistic)
    }

}