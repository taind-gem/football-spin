package com.tafi.footballspin.recyclerview.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.tafi.footballspin.R

/**
 * Created by taind-201 on 3/3/2020.
 */

class EmptyViewHolder constructor(itemView: View, var type: Int) : BaseViewHolder(itemView) {

    private var message = itemView.findViewById<TextView>(R.id.message)
    private var image = itemView.findViewById<ImageView>(R.id.image)

    override fun clear() {

    }

    override fun onBind(position: Int) {
        super.onBind(position)
        when (type) {
            TYPE_MATCH -> {
                message.text = itemView.context.getString(R.string.no_match)
                image.setImageResource(R.drawable.ic_match_sample)
            }
            TYPE_PLAYER -> {
                message.text = itemView.context.getString(R.string.no_player_content)
                image.setImageResource(R.drawable.ic_player_sample)
            }
            TYPE_TEAM -> {
                message.text = itemView.context.getString(R.string.no_team)
                image.setImageResource(R.drawable.ic_team_sample)
            }
        }
    }

    companion object {
        const val TYPE_PLAYER = 1
        const val TYPE_TEAM = 2
        const val TYPE_MATCH = 3

    }

}