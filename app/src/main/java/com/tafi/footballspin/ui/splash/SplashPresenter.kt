package com.tafi.footballspin.ui.splash

import android.os.Handler
import com.tafi.footballspin.data.DataManager
import com.tafi.footballspin.model.entity.LoggedInMode
import com.tafi.footballspin.ui.base.BasePresenter
import com.tafi.footballspin.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SplashPresenter<V : ISplashView> @Inject constructor(
    override var mDataManager: DataManager,
    override var mSchedulerProvider: SchedulerProvider,
    override var mCompositeDisposable: CompositeDisposable
) : BasePresenter<V>(mDataManager, mSchedulerProvider, mCompositeDisposable), ISplashPresenter<V> {

    override fun onAttach(view: V) {
        super.onAttach(view)

        mView?.startSyncService()

        Handler().postDelayed({
            decideNextActivity()
        }, 1500)
    }


    override fun decideNextActivity() {
        if (mDataManager.getCurrentUserLoggedInMode() == LoggedInMode.MODE_LOGGED_OUT.type) {
//            mView?.openLoginActivity()
            mView?.openMainActivity()
        } else {
            mView?.openMainActivity()
        }
    }
}