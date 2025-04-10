package com.example.hackaton.di.auth

import com.example.hackaton.network.auth.data.repository.AuthRepositoryImpl
import com.example.hackaton.network.auth.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

val AuthModule = module {
    single { FirebaseAuth.getInstance() }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
}