package com.tafi.footballspin.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tafi.footballspin.R
import com.tafi.footballspin.data.db.model.Player
import com.tafi.footballspin.recyclerview.ViewType.VIEW_TYPE_EMPTY
import com.tafi.footballspin.recyclerview.ViewType.VIEW_TYPE_NORMAL
import com.tafi.footballspin.recyclerview.viewholder.BaseViewHolder

/**
 * Created by taind-201 on 3/1/2020.
 */
class PlayerAdapter constructor(var context: Context) :
    RecyclerView.Adapter<BaseViewHolder>() {

    var viewType = VIEW_TYPE_EMPTY

    var mPlayerList: List<Player>? = null
        set(value) {
            field = value
            viewType = if (value.isNullOrEmpty()) VIEW_TYPE_EMPTY else VIEW_TYPE_NORMAL
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        return when (viewType) {
            VIEW_TYPE_NORMAL -> PlayerViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.row_player, parent, false)
            )
            else -> EmptyViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.row_no_player,
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

        override fun clear() {}

        override fun onBind(position: Int) {
            super.onBind(position)
            mPlayerList?.get(position)?.let { player ->
                val resourceId: Int = itemView.context.resources.getIdentifier(
                    player.nickname,
                    "drawable",
                    itemView.context.packageName
                )
                imgAvatar.setImageResource(resourceId)
                tvName.text = player.name
            }
        }
    }


    class EmptyViewHolder constructor(itemView: View) : BaseViewHolder(itemView) {
        override fun clear() {

        }
    }

}