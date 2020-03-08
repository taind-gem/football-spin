package com.tafi.footballspin.ui.login

import com.tafi.footballspin.R
import com.tafi.footballspin.data.DataManager
import com.tafi.footballspin.ui.base.BasePresenter
import com.tafi.footballspin.utils.CommonUtils
import com.tafi.footballspin.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by taind-201 on 2/11/2020.
 */

class LoginPresenter<V : ILoginView> @Inject constructor(
    override var mDataManager: DataManager,
    override var mSchedulerProvider: SchedulerProvider,
    override var mCompositeDisposable: CompositeDisposable
) : BasePresenter<V>(mDataManager, mSchedulerProvider, mCompositeDisposable),
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

//        mCompositeDisposable.add(
//            mAppNetworkManager
//                .doServerLoginApiCall(LoginRequest.ServerLoginRequest(email, password))
//                .subscribeOn(mSchedulerProvider.io())
//                .observeOn(mSchedulerProvider.ui())
//                .subscribe(
//                    { response ->
//                        Timber.i(response.toString())
//                        if (!isViewAttached()) return@subscribe
//                        mView?.hideLoading()
//
//                        when (response.statusCode) {
//                            200 -> {
//                                response.profile?.let { profile ->
//                                    mDataManager.updateUserInfo(
//                                        profile.accessToken,
//                                        profile.userId,
//                                        LoggedInMode.MODE_SERVER,
//                                        profile.userName,
//                                        profile.userEmail,
//                                        profile.serverProfilePicUrl
//                                    )
//                                }
//                                mView?.openMainActivity()
//                            }
//                            else -> {
//                                mView?.onError("[ERR" + response.statusCode + "] " + response.message)
//                            }
//                        }
//
//                    },
//                    { throwable ->
//                        Timber.e(throwable)
//                        if (!isViewAttached()) return@subscribe
//
//                        mView?.hideLoading()
//                        mView?.onError(throwable.message)
//
//                    }
//                )
//        )

    }

    override fun onGoogleLoginClick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFacebookLoginClick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}