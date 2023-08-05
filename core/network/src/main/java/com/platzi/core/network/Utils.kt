package com.platzi.core.network

import android.annotation.SuppressLint
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

fun unsafeOkHttpClient(vararg interceptors: HttpLoggingInterceptor): OkHttpClient =
    // Install the all-trusting trust manager
    // Create an ssl socket factory with our all-trusting manager
    try {
        // Create a trust manager that does not validate certificate chains
        val trustAllCerts = arrayOf<TrustManager>(trustManager)

        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())
        val sslSocketFactory = sslContext.socketFactory
        val builder = OkHttpClient.Builder()
        builder.sslSocketFactory(sslSocketFactory, trustManager)
        builder.hostnameVerifier { _, _ -> true }
        interceptors.forEach {
            builder.addInterceptor(it)
        }
        builder.build()

    } catch (e: Exception) {
        throw RuntimeException(e)
    }

private val trustManager =
    @SuppressLint("CustomX509TrustManager")
    object : X509TrustManager {
        @Throws(CertificateException::class)
        override fun checkClientTrusted(
            chain: Array<X509Certificate>,
            authType: String
        ) {
        }

        @Throws(CertificateException::class)
        override fun checkServerTrusted(
            chain: Array<X509Certificate>,
            authType: String
        ) {
        }

        override fun getAcceptedIssuers() = arrayOf<X509Certificate>()
    }