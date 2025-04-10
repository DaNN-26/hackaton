package com.example.hackaton.di.firestore

import com.example.hackaton.network.firestore.user.data.repository.UserRepositoryImpl
import com.example.hackaton.network.firestore.user.domain.repository.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

val FirestoreModule = module {
    single { FirebaseFirestore.getInstance() }
    single<UserRepository> { UserRepositoryImpl(get()) }
}