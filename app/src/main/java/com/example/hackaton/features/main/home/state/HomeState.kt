package com.example.hackaton.features.main.home.state

import kotlinx.serialization.Serializable

@Serializable
data class HomeState(
    val documents: List<Int> = emptyList()
)
