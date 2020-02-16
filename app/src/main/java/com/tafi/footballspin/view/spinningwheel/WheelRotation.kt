package com.tafi.footballspin.view.spinningwheel

import android.os.CountDownTimer

internal class WheelRotation(millisInFuture: Long, countDownInterval: Long) :
    CountDownTimer(millisInFuture, countDownInterval) {

    private var maxAngle = 0f
    private var angle = 1f
    private val thresholdSlow: Long
    private var rotationListener: RotationListener? = null
    private val duration: Long

    fun setMaxAngle(maxAngle: Float): WheelRotation {
        this.maxAngle = maxAngle
        return this
    }

    fun setListener(l: RotationListener?): WheelRotation {
        rotationListener = l
        return this
    }

    override fun onTick(millisUntilFinished: Long) {
        if (rotationListener == null) {
            return
        }
        if (millisUntilFinished <= thresholdSlow) {
            angle = maxAngle * (millisUntilFinished.toFloat() / duration.toFloat())
            rotationListener!!.onRotate(angle)
        } else if (angle < maxAngle) {
            rotationListener!!.onRotate(angle)
            angle *= ROTATE_SCALE_FACTOR
            if (angle > maxAngle) {
                angle = maxAngle
            }
        } else {
            rotationListener!!.onRotate(angle)
        }
    }

    override fun onFinish() {
        if (rotationListener == null) {
            return
        }
        rotationListener!!.onStop()
    }

    interface RotationListener {
        fun onRotate(angle: Float)
        fun onStop()
    }

    companion object {
        private const val ROTATE_SCALE_FACTOR = 2f
        private const val SLOW_FACTOR = 2f / 3f

        /**
         * @param millisInFuture    The number of millis in the future from the call
         * to [.start] until the countdown is done and [.onFinish]
         * is called.
         * @param countDownInterval The interval along the way to receive
         * [.onTick] callbacks.
         */
        @JvmStatic
        fun init(millisInFuture: Long, countDownInterval: Long): WheelRotation {
            return WheelRotation(millisInFuture, countDownInterval)
        }
    }

    init {
        thresholdSlow = (millisInFuture * SLOW_FACTOR).toLong()
        duration = millisInFuture
    }
}