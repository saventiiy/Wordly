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
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        CollectionUiState -> CollectionView(
            onCreateCollectionClicked = { name, collection ->
                viewModel.createCollection(name = name, words = collection)
                navController.popBackStack()
            }
        )
    }
}