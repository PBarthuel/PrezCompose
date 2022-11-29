package com.barthuel.prezcompose.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.barthuel.prezcompose.R

@Composable
fun BigCard(
    onCardClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .height(128.dp)
            .width(326.dp)
            .padding(8.dp)
            .clickable { onCardClicked() }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.align(Alignment.Center)) {
                Icon(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    painter = painterResource(id = R.drawable.ic_baseline_surfing_24),
                    contentDescription = null
                )
                Text(
                    text = "Patate",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}