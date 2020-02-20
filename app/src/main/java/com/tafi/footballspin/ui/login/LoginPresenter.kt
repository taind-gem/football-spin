package com.tafi.footballspin.ui.login

import com.tafi.footballspin.R
import com.tafi.footballspin.data.DataManager
import com.tafi.footballspin.model.entity.LoggedInMode
import com.tafi.footballspin.network.NetworkManager
import com.tafi.footballspin.network.request.LoginRequest
import com.tafi.footballspin.ui.base.BasePresenter
import com.tafi.footballspin.utils.CommonUtils
import com.tafi.footballspin.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by taind-201 on 2/11/2020.
 */

class LoginPresenter<V : ILoginView> @Inject constructor(
    override var mDataManager: DataManager,
    override var mNetworkManager: NetworkManager,
    override var mSchedulerProvider: SchedulerProvider,
    override var mCompositeDisposable: CompositeDisposable
) : BasePresenter<V>(mDataManager, mNetworkManager, mSchedulerProvider, mCompositeDisposable),
    ILoginPresenter<V> {

    override fun onServerLoginClick(email: String?, password: String?) {

        if (email == null || email.isEmpty()) {
            mView?.onError(R.string.empty_email)
            return
        }
        if (!CommonUtils.isEmailValid(email)) {
            mView?.onError(R.string.invalid_email)
            return
        }
        if (password == null || password.isEmpty()) {
            mView?.onError(R.string.empty_password)
            return
        }
        mView?.showLoading()

        mCompositeDisposable.add(
            mNetworkManager
                .doServerLoginApiCall(LoginRequest.ServerLoginRequest(email, password))
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(
                    { response ->
                        mDataManager.updateUserInfo(
                            response.accessToken,
                            response.userId,
                            LoggedInMode.MODE_SERVER,
                            response.userName,
                            response.userEmail,
                            response.serverProfilePicUrl
                        )

                        if (!isViewAttached()) {
                            return@subscribe
                        }

                        mView?.hideLoading()
                        mView?.openMainActivity()

                    },
                    { throwable ->
                        if (!isViewAttached()) {
                            return@subscribe
                        }

                        mView?.hideLoading()
                        mView?.onError(throwable.message)
                        Timber.e(throwable)

                    }
                )
        )

    }

    override fun onGoogleLoginClick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFacebookLoginClick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}