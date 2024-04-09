package com.saventiy.wordly.screens.collection.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saventiy.wordly.R
import com.saventiy.wordly.ui.theme.Alto

@Composable
fun CollectionView(
    modifier: Modifier = Modifier,
    words: MutableList<String>,
    onAddWordClicked: (String) -> Unit,
    createCollectionClicked: (String, MutableList<String>) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Alto)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var name by remember { mutableStateOf(TextFieldValue("")) }
//        val isValid = remember { mutableStateOf(!(name.text.isEmpty() || words.isEmpty())) }
        val isValid = remember { mutableStateOf(false) }
        LaunchedEffect(name, words) {
            isValid.value = !(name.text.isEmpty() || words.isEmpty())
        }

        OutlinedTextField(modifier = modifier.fillMaxWidth(),
            value = name,
            onValueChange = { name = it },
            label = { Text(text = stringResource(id = R.string.enter_collection_name)) })

        AddWordView(modifier = Modifier.fillMaxWidth(), words = words) {
            onAddWordClicked.invoke(it)
        }
        FirstButton(
            text = R.string.save,
            enabled = isValid.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 140.dp)
        ) {
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
        OutlinedTextField(modifier = modifier.padding(top = 10.dp),
            value = word,
            onValueChange = { word = it },
            label = { Text(text = stringResource(id = R.string.app_name)) })
        FirstButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            text = R.string.add
        ) {
            onButtonClicked.invoke(word.text)
            word = TextFieldValue("")
        }
        Text(
            text = words.joinToString(", "),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
        )
    }
}

@Preview
@Composable
fun Preview() {
    CollectionView(words = mutableListOf("word", "word1", "words2"),
        onAddWordClicked = {},
        createCollectionClicked = { _, _ -> })
}


@Composable
fun FirstButton(
    modifier: Modifier = Modifier,
    text: Int,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(modifier = modifier, enabled = enabled, onClick = { onClick.invoke() }) {
        Text(text = stringResource(id = text))
    }
}

@Composable
fun OutlinedTextFieldWordly(modifier: Modifier = Modifier, label: Int) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(modifier = modifier,
        value = text,
        onValueChange = { text = it },
        label = { Text(text = stringResource(id = label)) })
}
