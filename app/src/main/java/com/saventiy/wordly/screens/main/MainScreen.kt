package com.saventiy.wordly.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.saventiy.wordly.screens.main.view.MainView

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val collections by viewModel.uiState.collectAsStateWithLifecycle()

    when (collections) {
        is MainUiState.Error -> {}
        MainUiState.Loading -> {}
        is MainUiState.Success -> MainView(
            collections = (collections as MainUiState.Success).collections.toMutableList(),
            navController = navController,
            onDeleteClicked = {
                viewModel.deleteCollection(it)
            }
        )
    }
}