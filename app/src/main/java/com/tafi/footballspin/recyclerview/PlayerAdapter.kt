package com.tafi.footballspin.recyclerview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tafi.footballspin.R
import com.tafi.footballspin.data.db.model.Player
import com.tafi.footballspin.recyclerview.ViewType.VIEW_TYPE_EMPTY
import com.tafi.footballspin.recyclerview.ViewType.VIEW_TYPE_LOADING
import com.tafi.footballspin.recyclerview.ViewType.VIEW_TYPE_NORMAL
import com.tafi.footballspin.recyclerview.viewholder.BaseViewHolder
import com.tafi.footballspin.recyclerview.viewholder.EmptyViewHolder
import com.tafi.footballspin.recyclerview.viewholder.LoadingViewHolder
import com.tafi.footballspin.ui.splash.SplashActivity
import com.tafi.footballspin.ui.teamselect.TeamSelectActivity
import com.tafi.footballspin.utils.AppConstants
import com.tafi.footballspin.utils.AppConstants.REQUEST_CODE_TEAM_SELECT

/**
 * Created by taind-201 on 3/1/2020.
 */
class PlayerAdapter constructor(var context: Context) :
    RecyclerView.Adapter<BaseViewHolder>() {

    private var viewType = VIEW_TYPE_LOADING

    var mPlayerList: List<Player>? = null
        set(value) {
            value?.let { players ->
                for (player in players) {
                    player.isJoin = true
                }
            }
            field = value
            viewType = if (value.isNullOrEmpty()) VIEW_TYPE_EMPTY else VIEW_TYPE_NORMAL
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        return when (viewType) {
            VIEW_TYPE_NORMAL -> PlayerViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.row_player, parent, false)
            )
            VIEW_TYPE_LOADING -> LoadingViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.row_loading, parent, false)
            )
            else -> EmptyViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.row_empty,
                    parent,
                    false
                )
            )
        }

    }

    override fun getItemCount(): Int {
        return if (mPlayerList.isNullOrEmpty()) {
            1
        } else {
            mPlayerList!!.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    inner class PlayerViewHolder constructor(itemView: View) : BaseViewHolder(itemView) {

        private var imgAvatar = itemView.findViewById<ImageView>(R.id.img_avatar)
        private var tvName = itemView.findViewById<TextView>(R.id.tv_name)
        private var imgCheck = itemView.findViewById<ImageView>(R.id.img_check)
        private var btnSelectTeam = itemView.findViewById<Button>(R.id.btn_select_team)

        override fun clear() {}

        override fun onBind(position: Int) {
            super.onBind(position)
            mPlayerList?.get(position)?.let { player ->
                val resourceId: Int = itemView.context.resources.getIdentifier(
                    player.avatarUrl,
                    "drawable",
                    itemView.context.packageName
                )
                imgAvatar.setImageResource(resourceId)
                tvName.text = player.nickname

                setCheckButton(player.isJoin)

                imgCheck.setOnClickListener {
                    setCheckButton(!player.isJoin)
                    player.isJoin = !player.isJoin
                }

                btnSelectTeam.setOnClickListener {
                    openTeamSelectActivity(mPlayerList!![position])
                }

                val teamNumber =
                    if (player.listTeamIdSelected != null) player.listTeamIdSelected.size
                    else 0
                btnSelectTeam.text = "$teamNumber/8"
            }
        }

        private fun setCheckButton(isCheck: Boolean) {
            if (isCheck) {
                imgCheck.apply {
                    setImageResource(R.drawable.ic_check)
                    setBackgroundResource(R.color.check_bg)
                }
            } else {
                imgCheck.apply {
                    setImageResource(R.drawable.ic_uncheck)
                    setBackgroundResource(R.color.uncheck_bg)
                }
            }
        }

        private fun openTeamSelectActivity(player: Player) {
            (context as? SplashActivity)?.apply {
                val intent = Intent(this, TeamSelectActivity::class.java)
                intent.putExtra(AppConstants.EXTRA_PLAYER, player.convertPlayerToString())
                startActivityForResult(intent, REQUEST_CODE_TEAM_SELECT)
            }
        }
    }

}