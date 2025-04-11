package com.example.hackaton.features.auth.signUp.intent

sealed class SignUpIntent {
    class OnFirstnameChange(val firstname:String): SignUpIntent()
    class OnLastnameChange(val lastname:String): SignUpIntent()
    class OnPatronymicChange(val patronymic:String): SignUpIntent()
    object NavigateNext: SignUpIntent()
    object NavigateBack: SignUpIntent()
}