package com.saventiy.wordly.screens.main

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saventiy.wordly.data.model.dto.Collection
import com.saventiy.wordly.domain.usecase.local.AddCollectionUseCase
import com.saventiy.wordly.domain.usecase.local.GetAllCollectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    val addCollectionUseCase: AddCollectionUseCase,
    val getAllCollectionsUseCase: GetAllCollectionsUseCase
) : ViewModel() {

    fun pushCollection(){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val collection = Collection("init", listOf("word", "word1", "word2", "word3", "word4", "word5", "word6", "word7"), isActive = true)
                addCollectionUseCase.invoke(collection)
            }
        }
    }
}

