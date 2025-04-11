package com.example.hackaton.features.main.home.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.example.hackaton.features.main.home.intent.HomeIntent
import com.example.hackaton.features.main.home.state.HomeState

class DefaultHomeComponent(
    componentContext: ComponentContext
) : HomeComponent, ComponentContext by componentContext {

    private val _state = MutableValue(
        stateKeeper.consume(HOME_COMPONENT, HomeState.serializer()) ?: HomeState()
    )

    override val state = _state

    override fun processIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.GetDocuments -> { /*TODO*/ }
            is HomeIntent.OnDocumentClick -> { /*TODO*/ }
        }
    }

    companion object {
        const val HOME_COMPONENT = "HOME_COMPONENT"
    }

}