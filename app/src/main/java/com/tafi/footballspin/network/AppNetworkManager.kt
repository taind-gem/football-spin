package com.tafi.footballspin.network

import android.content.Context
import android.os.Build
import com.google.gson.Gson
import com.tafi.footballspin.BuildConfig
import com.tafi.footballspin.R
import com.tafi.footballspin.network.request.LoginRequest
import com.tafi.footballspin.network.response.LoginResponse
import com.tafi.footballspin.di.scope.ApplicationContext
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by taind-201 on 2/20/2020.
 */

class AppNetworkManager @Inject constructor(@ApplicationContext context: Context) : NetworkManager {

    private var retrofit: Retrofit
    private var apiService: ApiService

    companion object {
        const val REQUEST_TIMEOUT = 30L
    }

    init {
        val okHttpClient = initOkHttp()

        retrofit = Retrofit.Builder()
            .baseUrl(context.getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    private fun initOkHttp(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        if (BuildConfig.DEBUG) {
            httpClient.interceptors().add(logging)
        }

        httpClient.addInterceptor { chain ->
            val original: Request = chain.request()
            val requestBuilder = original.newBuilder()
                .header("User-Agent", createOriginalUserAgent())
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .method(original.method(), original.body())

            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }

        return httpClient.build()
    }

    private fun createOriginalUserAgent(): String {
        return "TAFI/${BuildConfig.VERSION_NAME} (${Build.MODEL})"
    }

    override fun doServerLoginApiCall(request: LoginRequest.ServerLoginRequest): Single<LoginResponse> {
        return apiService.getUser(request)
    }

    override fun doGoogleLoginApiCall(request: LoginRequest.GoogleLoginRequest): Single<LoginResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun doFacebookLoginApiCall(request: LoginRequest.FacebookLoginRequest): Single<LoginResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}