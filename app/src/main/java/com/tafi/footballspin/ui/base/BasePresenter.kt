package com.tafi.footballspin.ui.base

import com.tafi.footballspin.data.DataManager
import com.tafi.footballspin.model.ApiError
import com.tafi.footballspin.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by taind-201 on 2/7/2020.
 */

open class BasePresenter<V : IView> : IPresenter<V> {

    private var mDataManager: DataManager? = null
    private var mSchedulerProvider: SchedulerProvider? = null
    private var mCompositeDisposable: CompositeDisposable? = null

    private var mView: V? = null

    @Inject
    open fun BasePresenter(
        dataManager: DataManager,
        schedulerProvider: SchedulerProvider?,
        compositeDisposable: CompositeDisposable?
    ) {
        mDataManager = dataManager
        mSchedulerProvider = schedulerProvider
        mCompositeDisposable = compositeDisposable
    }

    override fun onAttach(view: V) {
        mView = view
    }

    override fun onDetach() {
        mCompositeDisposable!!.dispose()
        mView = null
    }

    override fun handleApiError(error: ApiError?) {
        if (error != null) {
            mView?.onError(error.message)
        }
    }

    override fun setUserAsLoggedOut() {
        mDataManager?.setAccessToken(null)
    }

}