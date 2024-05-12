package com.saventiy.wordly.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

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