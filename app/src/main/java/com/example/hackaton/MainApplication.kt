package com.example.hackaton

import android.app.Application
import com.example.hackaton.di.auth.AuthModule
import com.example.hackaton.di.firestore.FirestoreModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(AuthModule, FirestoreModule))
        }
    }
}