package com.saventiy.wordly.screens.main

import com.saventiy.wordly.data.model.dto.Collection

sealed interface MainUiState {
    object Loading : MainUiState
    data class Error(val throwable: Throwable) : MainUiState
    data class Success(val collections: List<Collection>) : MainUiState
}