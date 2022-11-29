package com.barthuel.prezcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.barthuel.prezcompose.ui.component.BigCard
import com.barthuel.prezcompose.ui.component.MediumCard
import com.barthuel.prezcompose.ui.component.SmallCard

@Composable
fun HomeScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        val list = listOf(1, 2, 3, 4, 5, 6, 7)
        items(list) { columnItem ->
            when (columnItem) {
                1 -> LazyRow { items(list) { SmallCard {} } }
                2 -> LazyRow { items(list) { MediumCard {} } }
                3 -> LazyRow { items(list) { BigCard {} } }
                4 -> LazyRow { items(list) { MediumCard {} } }
                5 -> LazyRow { items(list) { SmallCard {} } }
                6 -> LazyRow { items(list) { MediumCard {} } }
                7 -> LazyRow { items(list) { BigCard {} } }
            }
        }
    }
}
