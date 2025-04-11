package com.example.hackaton.features.auth.signUp.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.example.hackaton.features.auth.signUp.input_email.component.InputEmailComponent
import com.example.hackaton.features.auth.signUp.input_name.component.InputNameComponent

interface SignUpComponent {
    val stack: Value<ChildStack<*, Child>>

    sealed interface Child {
        class InputName(val component: InputNameComponent) : Child
        class InputEmail(val component: InputEmailComponent) : Child
    }
}