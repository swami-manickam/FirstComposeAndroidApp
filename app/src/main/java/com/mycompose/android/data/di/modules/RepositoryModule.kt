package com.mycompose.android.data.di.modules

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mycompose.android.app.AppController
import com.mycompose.android.data.api.ComposeApi
import com.mycompose.android.data.local.ComposeDatabase
import com.mycompose.android.data.preferences.AppPreference
import com.mycompose.android.data.repo.ComposeRepo
import com.mycompose.app.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


private const val CONNECT_TIMEOUT = 2
private const val READ_TIMEOUT = 2
private const val WRITE_TIMEOUT = 2


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {


    @Provides
    @Singleton
    fun providesContext(): Context {
        return AppController.getInstance()!!
    }

    @Provides
    @Singleton
    fun providesAppPreference(context: Context): AppPreference {
        return AppPreference(context)
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return GsonBuilder().serializeNulls().setLenient().create()
    }

    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
        return logging
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(logger: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(logger)
            .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.MINUTES)
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.MINUTES)
            .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.MINUTES).build()
    }

    @Provides
    @Singleton
    @Named("ApiLevel")
    fun providesRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.base_url)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.createAsync())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesAppRepo(
        httpClient: OkHttpClient,
        gson: Gson,
        @Named("AppBaseUrl") baseUrl: String,
        composeApi: ComposeApi,
        composeDatabase: ComposeDatabase
    ): ComposeRepo {
        Retrofit.Builder().client(httpClient).baseUrl(baseUrl)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.createAsync())
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
        return ComposeRepo(composeApi, composeDatabase)
    }

}