package com.tafi.footballspin.ui.splash

import android.os.Handler
import com.tafi.footballspin.data.DataManager
import com.tafi.footballspin.model.entity.LoggedInMode
import com.tafi.footballspin.network.NetworkManager
import com.tafi.footballspin.ui.base.BasePresenter
import com.tafi.footballspin.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SplashPresenter<V : ISplashView> @Inject constructor(
    override var mDataManager: DataManager,
    override var mNetworkManager: NetworkManager,
    override var mSchedulerProvider: SchedulerProvider,
    override var mCompositeDisposable: CompositeDisposable
) : BasePresenter<V>(mDataManager, mNetworkManager, mSchedulerProvider, mCompositeDisposable), ISplashPresenter<V> {

    override fun onAttach(view: V) {
        super.onAttach(view)

        mView?.startSyncService()

        Handler().postDelayed({
            mView?.openMainActivity()
        }, 1000)
    }


    override fun decideNextActivity() {
        if (mDataManager.getCurrentUserLoggedInMode() == LoggedInMode.MODE_LOGGED_OUT.type) {
            mView?.openLoginActivity()
        } else {
            mView?.openMainActivity()
        }
    }

}