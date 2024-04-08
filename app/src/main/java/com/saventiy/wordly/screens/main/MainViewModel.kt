package com.saventiy.wordly.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saventiy.wordly.data.model.dto.Collection
import com.saventiy.wordly.domain.usecase.local.GetAllCollectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getAllCollectionsUseCase: GetAllCollectionsUseCase
) : ViewModel() {

    val uiState: StateFlow<MainUiState> = getAllCollectionsUseCase.invoke()
        .map<List<Collection>, MainUiState> { MainUiState.Success(collections = it) }
        .catch { e -> emit(MainUiState.Error(e)) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MainUiState.Loading)

}
