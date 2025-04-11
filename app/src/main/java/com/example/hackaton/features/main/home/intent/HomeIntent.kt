package com.example.hackaton.features.main.home.intent

sealed class HomeIntent {
    object GetDocuments: HomeIntent()
    object OnDocumentClick: HomeIntent()
}