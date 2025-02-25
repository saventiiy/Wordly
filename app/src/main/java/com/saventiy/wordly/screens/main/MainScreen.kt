package com.saventiy.wordly.screens.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.saventiy.wordly.data.model.dto.Collection
import com.saventiy.wordly.screens.main.view.CollectionItem
import com.saventiy.wordly.ui.views.SwipeBackground

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val collections = mutableListOf<Collection>(
        Collection(
            "First", listOf("asdf", "word", "word1", "word2", "word3", "word4"), true
        ),
        Collection(
            "Second", listOf("asdf", "word", "word1", "word2", "word3", "word4"), false
        ),
        Collection(
            "Third", listOf("asdf", "word", "word1", "word2", "word3", "word4"), false
        ),
        Collection(
            "Fourth", listOf("asdf", "word", "word1", "word2", "word3", "word4"), false
        ),
        Collection(
            "Fifth", listOf("asdf", "word", "word1", "word2", "word3", "word4"), false
        ),
        Collection(
            "Sixth", listOf("asdf", "word", "word1", "word2", "word3", "word4"), false
        ),
        Collection(
            "Seventh", listOf("asdf", "word", "word1", "word2", "word3", "word4"), false
        )
    )
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                          viewModel.pushCollection()
                },
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                item {
                    Spacer(modifier = Modifier.padding(8.dp))
                }
                items(count = collections.size) { index ->
                    val currentItem by rememberUpdatedState(index)
                    val dismissState = rememberDismissState(
                        confirmStateChange = {
                            if (it == DismissValue.DismissedToStart || it == DismissValue.DismissedToEnd) {
                                collections.removeAt(currentItem)
                                true
                            } else false
                        }
                    )

                    if (dismissState.isDismissed(DismissDirection.EndToStart) ||
                        dismissState.isDismissed(DismissDirection.StartToEnd)
                    ) {
                        collections.removeAt(currentItem)
                    }

                    SwipeToDismiss(
                        state = dismissState,
                        modifier = Modifier
                            .padding(vertical = 1.dp)
                            .animateItemPlacement(),
                        directions = setOf(
                            DismissDirection.StartToEnd,
                            DismissDirection.EndToStart
                        ),
                        dismissThresholds = { direction ->
                            FractionalThreshold(
                                if (direction == DismissDirection.StartToEnd) 0.66f else 0.50f
                            )
                        },
                        background = {
                            SwipeBackground(dismissState)
                        },
                        dismissContent = {
                            CollectionItem(
                                collection = collections[currentItem]
                            ) {
                            }
                        }
                    )
//                    val currentItem by rememberUpdatedState(collections[index])
//                    val dismissState = rememberDismissState(
//                        confirmStateChange = {
//                            when (it) {
//                                DismissValue.DismissedToEnd -> {
//                                    // Do Something when swipe Start To End
//                                    true
//                                }
//
//                                DismissValue.DismissedToStart -> {
//                                    // Do Something when swipe End To Start
//                                    collections.remove(currentItem)
//                                    true
//                                }
//
//                                else -> {
//                                    false
//                                }
//                            }
//                        }
//                    )
//
//                    SwipeToDismiss(
//                        state = dismissState,
//                        modifier = Modifier
//                            .padding(vertical = 1.dp)
//                            .animateItemPlacement(),
//                        background = {
//                            SwipeBackground(dismissState)
//                        }
//                    ) {
//                        CollectionItem(collection = collections[index]) {
//
//                        }
//                    }
                }
                item {
                    Spacer(modifier = Modifier.padding(25.dp))
                }
            }
        }
    )
}