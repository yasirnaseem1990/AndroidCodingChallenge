package com.android.coding.challenge.util

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created on 9/28/2019
 * Email ynaseem@an10.io
 * Organization AN10
 */
class NetworkUtils(private val context: Context) {

    fun isConnected(): Boolean {
        if (context != null) {
            val mConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mNetworkInfo = mConnectivityManager.activeNetworkInfo
            if (mNetworkInfo != null) {
                return mNetworkInfo.isConnected
            }
        }
        return false
    }
}