package com.barthuel.prezcompose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InfosScreen() {
    val list = remember {
        mutableStateListOf(
            ListItem(id = 1, text = "Patate"),
            ListItem(id = 2, text = "Tomate"),
            ListItem(id = 3, text = "Courgette"),
            ListItem(id = 4, text = "Concombre")
        )
    }

    Column {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .background(Color.Cyan)
        ) {
            items(list, key = { it.id }) { item ->
                Row(
                    Modifier
                        .padding(12.dp)
                        .animateItemPlacement()
                ) { Text(text = item.text) }
            }
        }
        Button(
            modifier = Modifier.align(CenterHorizontally),
            onClick = { list.shuffle() }
        ) { Text(text = "Shuffle items") }
        Button(
            modifier = Modifier.align(CenterHorizontally),
            onClick = { list.add(ListItem(id = Random.nextInt(), text = "Added")) }
        ) { Text(text = "Add a item") }
    }
}

data class ListItem(
    val id: Int,
    val text: String
)
