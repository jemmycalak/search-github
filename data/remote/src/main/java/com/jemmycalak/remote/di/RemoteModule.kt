package com.jemmycalak.remote.di

import com.google.gson.GsonBuilder
import com.jemmycalak.remote.RepositoryDataSource
import com.jemmycalak.remote.api.RepositoryService
import com.jemmycalak.remote.utils.Constants.TIMEOUT
import com.jemmycalak.remote.utils.DataTypeAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val LOGGING_INTERCEPTOR = "LOGGING_INTERCEPTOR"

fun remoteModule(baseUrl:String) = module{

    single<Interceptor>(named(LOGGING_INTERCEPTOR)) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        GsonBuilder()
            .registerTypeAdapterFactory(DataTypeAdapterFactory())
            .setLenient()
            .create()
    }

    factory {
        OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(get(named(LOGGING_INTERCEPTOR)))
            .retryOnConnectionFailure(true)
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }

    factory { get<Retrofit>().create(RepositoryService::class.java) }

    factory { RepositoryDataSource(get()) }
}