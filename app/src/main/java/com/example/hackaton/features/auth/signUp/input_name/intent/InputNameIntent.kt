package com.example.hackaton.features.auth.signUp.input_name.intent

sealed class InputNameIntent {
    class OnFirstnameChange(val firstname:String): InputNameIntent()
    class OnLastnameChange(val lastname:String): InputNameIntent()
    class OnPatronymicChange(val patronymic:String): InputNameIntent()
    object NavigateNext: InputNameIntent()
    object NavigateBack: InputNameIntent()
}