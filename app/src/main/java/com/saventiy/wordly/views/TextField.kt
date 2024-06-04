package com.saventiy.wordly.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun OutlinedTextFieldWordly(
    modifier: Modifier = Modifier,
    label: Int,
    value: MutableState<String> = mutableStateOf(""),
    onValueChanged: (String) -> Unit
) {
    val text = if (value.value == "") remember { mutableStateOf("") } else value
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = text.value,
        onValueChange = {
            text.value = it
            onValueChanged.invoke(it)
        },
        label = { Text(text = stringResource(id = label)) }
    )
}
