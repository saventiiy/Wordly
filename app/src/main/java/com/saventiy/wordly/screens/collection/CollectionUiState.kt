package com.saventiy.wordly.screens.collection

sealed interface CollectionUiState {
    object Loading : CollectionUiState
    data class Error(val throwable: Throwable) : CollectionUiState
    object Success : CollectionUiState
}