package com.android.coding.challenge.di.module

import android.annotation.SuppressLint
import com.android.coding.challenge.AppController
import com.android.coding.challenge.BuildConfig
import com.google.gson.GsonBuilder
import com.android.coding.challenge.network.Service
import com.android.coding.challenge.util.NetworkUtils
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import javax.security.cert.CertificateException
import java.io.IOException


val appModules = module {

    single {
        createWebService<Service>(
            createHttpClient(),
            BuildConfig.API_BASE_URL
        )
    }

}


fun createHttpClient(): OkHttpClient {
    val cacheSize = (10 * 1024 * 1024).toLong() // 10MB
    val myCache = Cache(AppController.ApplicationContext.cacheDir, cacheSize)
    val client = OkHttpClient.Builder()
    client.readTimeout(5 * 60, TimeUnit.SECONDS)
    client.cache(myCache)
    client.addInterceptor(provideHttpLoggingInterceptor())
    return client.addInterceptor {
        val original = it.request()
        val requestBuilder = original.newBuilder()
        if (NetworkUtils(AppController.ApplicationContext).isConnected()) {
            val maxAge = 5 // If there is Internet, get the cache that was stored 5 seconds ago.
            requestBuilder.header("Cache-Control", "public, max-age=$maxAge")
            requestBuilder.header("Content-Type", "application/json")
        } else {
            val maxStale = 600  // tolerate 10 minutes stale
            requestBuilder.header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
        }
        val request = requestBuilder.method(original.method(), original.body()).build()

        return@addInterceptor it.proceed(request)
    }.build()
}



private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    return logging
}


inline fun <reified T> createWebService(okHttpClient: OkHttpClient, baseUrl: String): T {

    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}


