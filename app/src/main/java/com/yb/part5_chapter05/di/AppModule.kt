package com.yb.part5_chapter05.di

import android.app.Activity
import com.google.firebase.ktx.BuildConfig
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.yb.part5_chapter05.data.api.StationApi
import com.yb.part5_chapter05.data.api.StationArrivalsApi
import com.yb.part5_chapter05.data.api.StationStorageApi
import com.yb.part5_chapter05.data.api.Url
import com.yb.part5_chapter05.data.db.AppDatabase
import com.yb.part5_chapter05.data.preference.PreferenceManager
import com.yb.part5_chapter05.data.preference.SharedPreferenceManager
import com.yb.part5_chapter05.data.repository.StationRepository
import com.yb.part5_chapter05.data.repository.StationRepositoryImpl
import com.yb.part5_chapter05.presentation.stations.StationsContract
import com.yb.part5_chapter05.presentation.stations.StationsFragment
import com.yb.part5_chapter05.presentation.stations.StationsPresenter
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val appModule = module {

    //Dispatchers
    single { Dispatchers.IO }

    // Database
    single { AppDatabase.build(androidApplication()) }
    single { get<AppDatabase>().stationDao() }

    // Preference
    single { androidContext().getSharedPreferences("preference", Activity.MODE_PRIVATE) }
    single<PreferenceManager> { SharedPreferenceManager(get()) }

    // Api
    single<StationApi> { StationStorageApi(Firebase.storage) }

    single {
        OkHttpClient()
            .newBuilder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    this.level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                }
            )
            .build()
    }

    single<StationArrivalsApi> {
        Retrofit.Builder()
            .baseUrl(Url.SEOUL_DATA_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create()
    }

    // Repository
    single<StationRepository> { StationRepositoryImpl(get(), get(), get(), get(), get()) }

    // Presentation
    scope<StationsFragment> {
        scoped<StationsContract.Presenter> {
            StationsPresenter(getSource(), get())
        }
    }
}