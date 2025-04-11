package com.example.hackaton.features.main.home.component

import com.arkivanov.decompose.value.Value
import com.example.hackaton.features.main.home.intent.HomeIntent
import com.example.hackaton.features.main.home.state.HomeState

interface HomeComponent {
    val state: Value<HomeState>

    fun processIntent(intent: HomeIntent)
}