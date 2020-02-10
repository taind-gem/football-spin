package com.tafi.footballspin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by taind-201 on 2/11/2020.
 */
class ApiError(
    var errorCode: Int,
    @field:SerializedName("status_code") @field:Expose var statusCode: String?,
    @field:SerializedName("message") @field:Expose var message: String?
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val apiError = other as ApiError
        if (errorCode != apiError.errorCode) return false
        if (if (statusCode != null) statusCode != apiError.statusCode else apiError.statusCode != null) return false
        return if (message != null) message == apiError.message else apiError.message == null
    }

    override fun hashCode(): Int {
        var result = errorCode
        result = 31 * result + if (statusCode != null) statusCode.hashCode() else 0
        result = 31 * result + if (message != null) message.hashCode() else 0
        return result
    }

}