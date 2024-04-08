package com.saventiy.wordly.screens.collection.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saventiy.wordly.R

@Composable
fun CollectionView(
    modifier: Modifier = Modifier,
    words: MutableList<String>,
    onAddWordClicked: (String) -> Unit,
    createCollectionClicked: (String, MutableList<String>) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var name by remember { mutableStateOf(TextFieldValue("")) }
        OutlinedTextField(
            modifier = modifier,
            value = name,
            onValueChange = { name = it },
            label = { Text(text = stringResource(id = R.string.enter_collection_name)) }
        )
//        OutlinedTextFieldWordly(label = R.string.enter_collection_name)

        AddWordView(words = words) {
            onAddWordClicked.invoke(it)
        }
        Spacer(modifier = Modifier.weight(1f))
        FirstButton(text = R.string.save, modifier = Modifier.padding(bottom = 40.dp)) {
            createCollectionClicked.invoke(name.text, words)
        }
    }
}

@Composable
fun AddWordView(
    modifier: Modifier = Modifier,
    words: MutableList<String>,
    onButtonClicked: (String) -> Unit
) {
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        var word by remember { mutableStateOf(TextFieldValue("")) }
        OutlinedTextField(
            modifier = modifier,
            value = word,
            onValueChange = { word = it },
            label = { Text(text = stringResource(id = R.string.app_name)) }
        )
        FirstButton(text = R.string.add) {
            onButtonClicked.invoke(word.text)
            word = TextFieldValue("")
        }
        Text(text = words.joinToString(", "), modifier = Modifier.padding(top = 15.dp))
    }
}

@Preview
@Composable
fun Preview() {
//    CollectionView()
}


@Composable
fun FirstButton(modifier: Modifier = Modifier, text: Int, onClick: () -> Unit) {
    Button(onClick = { onClick.invoke() }) {
        Text(text = stringResource(id = text))
    }
}

@Composable
fun OutlinedTextFieldWordly(modifier: Modifier = Modifier, label: Int) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = { text = it },
        label = { Text(text = stringResource(id = label)) }
    )
}
