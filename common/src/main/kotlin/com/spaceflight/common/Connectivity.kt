package com.spaceflight.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities


sealed class ConnectionState {
    data object Available : ConnectionState()
    data object Unavailable : ConnectionState()
}

fun Context.isInternetAvailable(): ConnectionState {
    val connected: Boolean
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities =
        connectivityManager.activeNetwork ?: return ConnectionState.Unavailable
    val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities)
        ?: return ConnectionState.Unavailable
    connected = when {
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
    }
    return if (connected) ConnectionState.Available else ConnectionState.Unavailable
}