package com.tafi.footballspin.ui.base

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.RelativeLayout
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

/**
 * Created by taind-201 on 3/8/2020.
 */
abstract class BaseDialog : DialogFragment(), IDialogView {

    private var mActivity: BaseActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            this.mActivity = context
            context.onFragmentAttached()
        }
    }

    override fun showLoading() {
        mActivity?.showLoading()
    }

    override fun hideLoading() {
        mActivity?.hideLoading()

    }

    override fun onError(message: String?) {
        mActivity?.onError(message)

    }

    override fun onError(@StringRes resId: Int) {
        mActivity?.onError(resId)

    }

    override fun showMessage(message: String?) {
        mActivity?.showMessage(message)
    }

    override fun showMessage(@StringRes resId: Int) {
        mActivity?.showMessage(resId)
    }

    override fun onDetach() {
        mActivity = null
        super.onDetach()
    }

    override fun hideKeyboard() {
        mActivity?.hideKeyboard()
    }

    override val isNetworkConnected: Boolean
        get() = mActivity != null && mActivity!!.isNetworkConnected

    override fun openActivityOnTokenExpire() {
        mActivity?.openActivityOnTokenExpire()
    }


    protected abstract fun setUp(view: View)

    @SuppressLint("InlinedApi")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val root = RelativeLayout(activity)
        root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        // creating the fullscreen dialog
        val dialog = Dialog(
            context!!,
            android.R.style.Theme_DeviceDefault_Light_Dialog_Alert
        )
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(root)
        if (dialog.window != null) {
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        dialog.setCanceledOnTouchOutside(false)

        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp(view)
    }

    override fun show(manager: FragmentManager, tag: String?) {
        val transaction = manager.beginTransaction()
        val prevFragment = manager.findFragmentByTag(tag)
        if (prevFragment != null) {
            transaction.remove(prevFragment)
        }
        transaction.addToBackStack(null)
        show(transaction, tag)

    }

    override fun dismissDialog(tag: String) {
        dismiss()
        mActivity?.onFragmentDetached(tag)
    }
}