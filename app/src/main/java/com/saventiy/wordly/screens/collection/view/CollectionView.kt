package com.saventiy.wordly.screens.collection.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saventiy.wordly.R
import com.saventiy.wordly.ui.theme.Alto
import com.saventiy.wordly.views.FirstButton
import com.saventiy.wordly.views.OutlinedTextFieldWordly
import com.saventiy.wordly.views.SecondButton

@Composable
fun CollectionView(
    modifier: Modifier = Modifier, onCreateCollectionClicked: (String, MutableList<String>) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Alto)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val name = remember { mutableStateOf(TextFieldValue("")) }
        val word = remember { mutableStateOf(("")) }
        val words = remember { mutableStateListOf<String>() }

        Spacer(modifier = Modifier.weight(1f))
        TextFieldsView(name = name, word = word, words = words)
        Spacer(modifier = Modifier.weight(1f))
        ButtonsView(isCreateCollectionButtonEnabled = !(name.value.text.isEmpty() || words.isEmpty()),
            onCreateCollectionClicked = {
                onCreateCollectionClicked.invoke(
                    name.value.text, words
                )
            },
            onAddWordClicked = {
                words.add(word.value)
                word.value = ""
            })
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TextFieldsView(
    modifier: Modifier = Modifier,
    name: MutableState<TextFieldValue>,
    word: MutableState<String>,
    words: MutableList<String>
) {
    OutlinedTextFieldWordly(label = R.string.enter_collection_name) {
        name.value = TextFieldValue(it)
    }
    OutlinedTextFieldWordly(
        modifier = Modifier.padding(top = 10.dp), label = R.string.app_name, value = word
    ) {
        word.value = it.trim()
    }

    FlowRow(modifier = Modifier.padding(5.dp)) {
        words.forEachIndexed { index, word ->
            WordItem(title = word) {
                words.removeAt(index)
            }
        }
    }
}

@Composable
fun ButtonsView(
    modifier: Modifier = Modifier,
    isCreateCollectionButtonEnabled: Boolean,
    onCreateCollectionClicked: () -> Unit,
    onAddWordClicked: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(top = 40.dp)
            .fillMaxWidth()
            .height(60.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FirstButton(
            text = R.string.save,
            enabled = isCreateCollectionButtonEnabled,
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        ) { onCreateCollectionClicked.invoke() }

        Spacer(modifier = Modifier.width(10.dp))
        SecondButton(icon = R.drawable.ic_plus) { onAddWordClicked.invoke() }
    }
}

@Preview
@Composable
fun Preview() {
    CollectionView(onCreateCollectionClicked = { _, _ -> })
}

