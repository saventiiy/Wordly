package com.saventiy.wordly.screens.main.view

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saventiy.wordly.R
import com.saventiy.wordly.data.model.dto.Collection
import com.saventiy.wordly.ui.theme.Alto

@Composable
fun CollectionItem(
//    modifier: Modifier = Modifier,
    collection: Collection,
    onClick: () -> Unit
) {
    val clicked = remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(containerColor = Alto, contentColor = Alto),
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures(onLongPress = {
                    collection.isActive = !collection.isActive
                })
            }
            .border(
                width = 2.dp,
                color = if (collection.isActive) Color.Black else Color.Unspecified,
                shape = RoundedCornerShape(25.dp)
            )
            .fillMaxWidth()
            .fillMaxHeight()
            .clickable {
                onClick.invoke()
                clicked.value = !clicked.value
            }) {

        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = collection.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(end = 15.dp)
                )

                Text(
                    text = collection.collection.size.toString(),
                    style = MaterialTheme.typography.titleMedium,
                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    painter = painterResource(if (clicked.value) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }

            Text(
                text = collection.collection.joinToString(", "),
                style = MaterialTheme.typography.titleSmall,
                maxLines = if (clicked.value) Int.MAX_VALUE else 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
    }
}


@Preview
@Composable
fun CollectionItemPreview() {
    CollectionItem(
        collection = Collection(
            name = "collection",
            collection = mutableListOf("word", "word1", "word2", "word3"),
            isActive = true
        )
    ) {}
}