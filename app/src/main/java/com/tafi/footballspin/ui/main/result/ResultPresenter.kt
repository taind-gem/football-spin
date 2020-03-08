package com.tafi.footballspin.ui.main.result

import com.tafi.footballspin.data.DataManager
import com.tafi.footballspin.ui.base.BasePresenter
import com.tafi.footballspin.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by taind-201 on 3/7/2020.
 */
class ResultPresenter<V : IResultView> @Inject constructor(
    override var mDataManager: DataManager,
    override var mSchedulerProvider: SchedulerProvider,
    override var mCompositeDisposable: CompositeDisposable
) : BasePresenter<V>(mDataManager, mSchedulerProvider, mCompositeDisposable),
    IResultPresenter<V> {

    override fun onViewInitialized() {

        mCompositeDisposable.add(
            mDataManager.getMatchList()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe { matches ->
                    mView?.apply {
                        updateMatchList(matches)
                        hideLoading()
                    }
                }
        )

    }

}