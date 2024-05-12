package com.saventiy.wordly.screens.collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saventiy.wordly.data.model.dto.Collection
import com.saventiy.wordly.domain.usecase.local.AddCollectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(private val addCollectionUseCase: AddCollectionUseCase) :
    ViewModel() {
    val uiState: StateFlow<CollectionUiState> = MutableStateFlow(CollectionUiState)

    fun createCollection(name: String, words: MutableList<String>) {
        if(name.isEmpty() || words.isEmpty()){
//            uiState. = CollectionUiState.Error()
        }
        viewModelScope.launch {
            addCollectionUseCase.invoke(
                collection = Collection(
                    name = name,
                    collection = words,
                    isActive = true
                )
            )
        }
    }
}