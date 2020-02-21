package com.tafi.footballspin.ui.base

import com.tafi.footballspin.data.DataManager
import com.tafi.footballspin.model.ApiError
import com.tafi.footballspin.network.AppNetworkManager
import com.tafi.footballspin.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by taind-201 on 2/7/2020.
 */

open class BasePresenter<V : IView> @Inject constructor(
    open var mDataManager: DataManager,
    open var mAppNetworkManager: AppNetworkManager,
    open var mSchedulerProvider: SchedulerProvider,
    open var mCompositeDisposable: CompositeDisposable
) : IPresenter<V> {

    var mView: V? = null

    override fun onAttach(view: V) {
        mView = view
    }

    fun isViewAttached() = mView != null

    override fun onDetach() {
        mCompositeDisposable.dispose()
        mView = null
    }

    override fun handleApiError(error: ApiError?) {
        if (error != null) {
            mView?.onError(error.message)
        }
    }

    override fun setUserAsLoggedOut() {
        mDataManager.setAccessToken(null)
    }

}