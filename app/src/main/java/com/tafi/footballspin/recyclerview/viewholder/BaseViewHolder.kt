package com.tafi.footballspin.recyclerview.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by taind-201 on 3/1/2020.
 */
abstract class BaseViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var mCurrentPosition: Int = 0

    protected abstract fun clear()

    open fun onBind(position: Int) {
        mCurrentPosition = position
        clear()
    }

}