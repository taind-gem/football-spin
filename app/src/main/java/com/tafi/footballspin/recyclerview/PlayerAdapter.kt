package com.tafi.footballspin.recyclerview

import android.app.Activity
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
import com.tafi.footballspin.ui.teamselect.TeamSelectActivity

/**
 * Created by taind-201 on 3/1/2020.
 */
class PlayerAdapter constructor(var context: Context) :
    RecyclerView.Adapter<BaseViewHolder>() {

    val selectedSet = hashSetOf<Long>()
    private var viewType = VIEW_TYPE_LOADING

    var mPlayerList: List<Player>? = null
        set(value) {
            field = value
            value?.let { players ->
                for (player in players) {
                    selectedSet.add(player.id)
                }
            }
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

                setCheckButton(selectedSet.contains(player.id))

                imgCheck.setOnClickListener {
                    val isCheck = selectedSet.contains(player.id)
                    setCheckButton(!isCheck)
                    if (isCheck) selectedSet.remove(player.id)
                    else selectedSet.add(player.id)
                }

                btnSelectTeam.setOnClickListener {
                    openTeamSelectActivity()
                }

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

        private fun openTeamSelectActivity(){
            (context as? Activity)?.apply {
                startActivity(Intent(this, TeamSelectActivity::class.java))
                finish()
            }
        }
    }

}