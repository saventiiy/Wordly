package com.saventiy.wordly

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.saventiy.wordly.screens.collection.CollectionScreen
import com.saventiy.wordly.screens.main.MainScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainScreen(navController = navController) }
        composable("collection") { CollectionScreen(navController = navController) }
    }

}