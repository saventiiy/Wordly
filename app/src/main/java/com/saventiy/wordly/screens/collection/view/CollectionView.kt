package com.saventiy.wordly.screens.collection.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
    onCreateCollectionClicked: (String, MutableList<String>) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Alto)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val name = remember { mutableStateOf(TextFieldValue("")) }
        val word = remember { mutableStateOf(TextFieldValue("")) }

        Spacer(modifier = Modifier.weight(1f))
        TextFieldsView(name = name, word = word, words = words)
        Spacer(modifier = Modifier.weight(1f))
        ButtonsView(
            isCreateCollectionButtonEnabled = !(name.value.text.isEmpty() || words.isEmpty()),
            onCreateCollectionClicked = {
                onCreateCollectionClicked.invoke(
                    name.value.text,
                    words
                )
            },
            onAddWordClicked = {
                onAddWordClicked.invoke(word.value.text)
                word.value = TextFieldValue("")
            })
    }
}

@Composable
fun TextFieldsView(
    modifier: Modifier = Modifier,
    name: MutableState<TextFieldValue>,
    word: MutableState<TextFieldValue>,
    words: MutableList<String>
) {
    OutlinedTextFieldWordly(label = R.string.enter_collection_name) {
        name.value = TextFieldValue(it)
    }
    OutlinedTextFieldWordly(
        modifier = Modifier.padding(top = 10.dp),
        label = R.string.app_name
    ) {
        word.value = TextFieldValue(it)
    }
    Text(
        text = words.joinToString(", "),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
    )
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
    CollectionView(words = mutableListOf("word", "word1", "words2"),
        onAddWordClicked = {},
        onCreateCollectionClicked = { _, _ -> })
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
fun SecondButton(
    modifier: Modifier = Modifier,
    icon: Int,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = { onClick.invoke() }) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.padding(10.dp)
        )
    }
}

@Composable
fun OutlinedTextFieldWordly(
    modifier: Modifier = Modifier,
    label: Int,
    onValueChanged: (String) -> Unit
) {
    val text = remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = text.value,
        onValueChange = {
            text.value = it
            onValueChanged.invoke(it.text)
        },
        label = { Text(text = stringResource(id = label)) }
    )
}
