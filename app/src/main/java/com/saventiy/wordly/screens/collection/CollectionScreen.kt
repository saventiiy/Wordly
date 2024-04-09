package com.saventiy.wordly.screens.collection

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.saventiy.wordly.screens.collection.view.CollectionView

@Composable
fun CollectionScreen(
    modifier: Modifier = Modifier,
    viewModel: CollectionViewModel = hiltViewModel(),
    navController: NavController
) {
    val words by viewModel.words.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        is CollectionUiState.Error -> {}
        CollectionUiState.Loading -> {}
        CollectionUiState.Success -> CollectionView(
            words = words,
            onAddWordClicked = { words.add(it) },
            createCollectionClicked = { name, collection ->
                viewModel.createCollection(name = name, words = collection)
                navController.popBackStack()
            }
        )
    }
}