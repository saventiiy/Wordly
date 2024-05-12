package com.saventiy.wordly.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue

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
