package com.tafi.footballspin.ui.base;

import org.jetbrains.annotations.NotNull;

/**
 * Created by taind-201 on 2/7/2020.
 */

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private static final String TAG = "BasePresenter";

    @Override
    public void onAttach(@NotNull V mvpView) {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void handleApiError(@NotNull String error) {

    }

    @Override
    public void setUserAsLoggedOut() {

    }
}
