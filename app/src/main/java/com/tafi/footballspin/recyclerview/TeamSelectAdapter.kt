package com.tafi.footballspin.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.Toast.LENGTH_LONG
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.tafi.footballspin.R
import com.tafi.footballspin.data.db.model.Team
import com.tafi.footballspin.recyclerview.ViewType.VIEW_TYPE_EMPTY
import com.tafi.footballspin.recyclerview.ViewType.VIEW_TYPE_LOADING
import com.tafi.footballspin.recyclerview.ViewType.VIEW_TYPE_NORMAL
import com.tafi.footballspin.recyclerview.viewholder.BaseViewHolder
import com.tafi.footballspin.recyclerview.viewholder.EmptyViewHolder
import com.tafi.footballspin.recyclerview.viewholder.LoadingViewHolder

/**
 * Created by taind-201 on 3/1/2020.
 */
class TeamSelectAdapter constructor(var context: Context) :
    RecyclerView.Adapter<BaseViewHolder>() {

    val selectedSet = hashSetOf<Long>()
    private var viewType = VIEW_TYPE_LOADING

    var mTeamList: List<Team>? = null
        set(value) {
            field = value
            viewType = if (value.isNullOrEmpty()) VIEW_TYPE_EMPTY else VIEW_TYPE_NORMAL
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        return when (viewType) {
            VIEW_TYPE_NORMAL -> TeamSelectViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.row_team_select, parent, false)
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
        return if (mTeamList.isNullOrEmpty()) {
            1
        } else {
            mTeamList!!.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    inner class TeamSelectViewHolder constructor(itemView: View) : BaseViewHolder(itemView) {

        private var rootView = itemView.findViewById<ConstraintLayout>(R.id.root_view)
        private var imgTeamLogo = itemView.findViewById<ImageView>(R.id.img_team_logo)
        private var tvTeamName = itemView.findViewById<TextView>(R.id.tv_team_name)
        private var tvLeagueName = itemView.findViewById<TextView>(R.id.tv_league_name)
        private var ratingBar = itemView.findViewById<RatingBar>(R.id.rating_bar)
        private val checkBox = itemView.findViewById<CheckBox>(R.id.check_box)

        override fun clear() {}

        override fun onBind(position: Int) {
            super.onBind(position)
            mTeamList?.get(position)?.let { team ->
                tvTeamName.text = team.name
                val resourceId: Int = itemView.context.resources.getIdentifier(
                    team.key,
                    "drawable",
                    itemView.context.packageName
                )
                tvLeagueName.text = team.leagueName
                ratingBar.rating = team.rate
                imgTeamLogo.setImageResource(resourceId)
                checkBox.isChecked = selectedSet.contains(team.id)

                rootView.setOnClickListener {
                    checkBox.isChecked = !checkBox.isChecked
                    setSelectTeam(team.id)
                }

                checkBox.setOnCheckedChangeListener { _, _ ->
                    setSelectTeam(team.id)
                }
            }
        }

        private fun setSelectTeam(teamId: Long) {
            if (checkBox.isChecked) selectedSet.add(teamId)
            else selectedSet.remove(teamId)
            Toast.makeText(context, selectedSet.toString(), LENGTH_LONG).show()
        }

    }

}