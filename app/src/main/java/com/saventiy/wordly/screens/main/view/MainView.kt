package com.saventiy.wordly.screens.main.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.saventiy.wordly.data.model.dto.Collection
import com.saventiy.wordly.screens.collection.CollectionScreen
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalMaterial3Api::class
)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainView(
    modifier: Modifier = Modifier,
    collections: MutableList<Collection>,
    navController: NavController,
    onDeleteClicked: (Collection) -> Unit
) {
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { openBottomSheet = true }
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        content = {
            if (openBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { openBottomSheet = false },
                    sheetState = bottomSheetState
                ) {
                    val coroutineScope = rememberCoroutineScope()
                    CollectionScreen {
                        coroutineScope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                            if (!bottomSheetState.isVisible) {
                                openBottomSheet = false
                            }
                        }
                    }
                }
            }
            //TODO remove items spacers because when deleting items, spaces still there
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                item {
                    Spacer(modifier = Modifier.padding(8.dp))
                }
                items(count = collections.size, key = { it }) { index ->
                    val currentItem by rememberUpdatedState(index)
                    SwipeToDeleteContainer(
                        item = collections[currentItem],
                        onDelete = {
                            onDeleteClicked.invoke(it)
                        }
                    ) { collection ->
                        CollectionItem(collection = collection) {}
                    }
                }
                item {
                    Spacer(modifier = Modifier.padding(25.dp))
                }
            }
        })
}