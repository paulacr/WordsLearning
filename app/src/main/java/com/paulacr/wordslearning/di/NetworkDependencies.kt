package com.paulacr.wordslearning.di

import android.content.res.Resources
import com.paulacr.wordslearning.R
import com.paulacr.wordslearning.network.ApiService
import com.paulacr.wordslearning.network.RequestInterceptor
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal const val DEFAULT_HTTP = "DEFAULT_HTTP"

internal val networkDependencies = module {

    factory {
        Retrofit.Builder()
            .baseUrl(get<Resources>().getString(R.string.base_url))
            .client(get(named(DEFAULT_HTTP)))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    factory(named(DEFAULT_HTTP)) {
        val showLogs = get<Resources>().getBoolean(R.bool.show_logging)
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (showLogs) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(RequestInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single { get<Retrofit>().create(ApiService::class.java) }
}
