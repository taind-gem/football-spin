package com.tafi.footballspin.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tafi.footballspin.R
import com.tafi.footballspin.data.db.model.Match
import com.tafi.footballspin.recyclerview.ViewType.VIEW_TYPE_EMPTY
import com.tafi.footballspin.recyclerview.ViewType.VIEW_TYPE_LOADING
import com.tafi.footballspin.recyclerview.ViewType.VIEW_TYPE_NORMAL
import com.tafi.footballspin.recyclerview.viewholder.BaseViewHolder
import com.tafi.footballspin.recyclerview.viewholder.EmptyViewHolder
import com.tafi.footballspin.recyclerview.viewholder.LoadingViewHolder
import com.tafi.footballspin.utils.CommonUtils

/**
 * Created by taind-201 on 3/1/2020.
 */
class MatchAdapter constructor(var context: Context) :
    RecyclerView.Adapter<BaseViewHolder>() {

    private var viewType = VIEW_TYPE_LOADING

    var mMatchList: List<Match>? = null
        set(value) {
            field = value
            viewType = if (value.isNullOrEmpty()) VIEW_TYPE_EMPTY else VIEW_TYPE_NORMAL
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        return when (viewType) {
            VIEW_TYPE_NORMAL -> PlayerViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.row_match_result, parent, false)
            )
            VIEW_TYPE_LOADING -> LoadingViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.row_loading, parent, false)
            )
            else -> EmptyViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.row_empty,
                    parent,
                    false
                ), EmptyViewHolder.TYPE_MATCH
            )
        }

    }

    override fun getItemCount(): Int {
        return if (mMatchList.isNullOrEmpty()) {
            1
        } else {
            mMatchList!!.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    inner class PlayerViewHolder constructor(itemView: View) : BaseViewHolder(itemView) {

        private val tvPlayerName1 = itemView.findViewById<TextView>(R.id.tv_player_name_1)
        private val tvPlayerName2 = itemView.findViewById<TextView>(R.id.tv_player_name_2)

        private val tvTeamName1 = itemView.findViewById<TextView>(R.id.tv_team_name_1)
        private val tvTeamName2 = itemView.findViewById<TextView>(R.id.tv_team_name_2)

        private val tvScore1 = itemView.findViewById<TextView>(R.id.edt_score_1)
        private val tvScore2 = itemView.findViewById<TextView>(R.id.edt_score_2)

        private val imgLogo1 = itemView.findViewById<ImageView>(R.id.img_logo_1)
        private val imgLogo2 = itemView.findViewById<ImageView>(R.id.img_logo_2)

        private val imgRed1 = itemView.findViewById<ImageView>(R.id.img_red_1)
        private val imgRed2 = itemView.findViewById<ImageView>(R.id.img_red_2)

        private val tvPoint1 = itemView.findViewById<TextView>(R.id.tv_point_1)
        private val tvPoint2 = itemView.findViewById<TextView>(R.id.tv_point_2)

        private val tvDate = itemView.findViewById<TextView>(R.id.tv_date)

        override fun clear() {}

        override fun onBind(position: Int) {
            super.onBind(position)

            mMatchList?.get(position)?.let {match ->
                tvPlayerName1.text = match.hostPlayer.nickname
                tvPlayerName2.text = match.guestPlayer.nickname

                tvTeamName1.text = match.hostTeam.name
                tvTeamName2.text = match.guestTeam.name

                tvScore1.text = match.statistic.hostScore.toString()
                tvScore2.text = match.statistic.guestScore.toString()

                imgRed1.visibility = if (match.statistic.red > 0) VISIBLE else GONE
                imgRed2.visibility = if (match.statistic.red < 0) VISIBLE else GONE

                tvDate.text = CommonUtils.convertTimeToString(match.createdTime)

                val sample = ContextCompat.getDrawable(context, R.drawable.ic_team_sample)
                Glide.with(context)
                    .load(CommonUtils.getDrawableByName(context, match.hostTeam.key))
                    .into(imgLogo1)
                    .onLoadFailed(sample)

                Glide.with(context)
                    .load(CommonUtils.getDrawableByName(context, match.guestTeam.key))
                    .into(imgLogo2)
                    .onLoadFailed(sample)
            }

        }

    }

}