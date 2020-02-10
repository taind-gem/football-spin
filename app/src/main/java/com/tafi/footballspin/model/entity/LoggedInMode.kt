package com.tafi.footballspin.model.entity

/**
 * Created by taind-201 on 2/11/2020.
 */

enum class LoggedInMode(val type: Int) {

    MODE_LOGGED_OUT(0),

    MODE_GOOGLE(1),

    MODE_FB(2),

    MODE_SERVER(3);

}