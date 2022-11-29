package com.barthuel.prezcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProfileScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                val pagerState = rememberPagerState()
                HorizontalPager(
                    count = 10,
                    state = pagerState,
                    // Add 32.dp horizontal padding to 'center' the pages
                    contentPadding = PaddingValues(horizontal = 32.dp),
                    modifier = Modifier.fillMaxWidth().align(CenterHorizontally),
                ) { page ->
                    SubcomposeAsyncImage(
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = null,
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQ_G9U9095poYEIvtg8fnA2Ef3dcjLEebptQ&usqp=CAU")
                            .crossfade(true)
                            .build(),
                        contentScale = ContentScale.Crop,
                    ) {
                        when (painter.state) {
                            AsyncImagePainter.State.Empty -> Unit
                            is AsyncImagePainter.State.Error,
                            is AsyncImagePainter.State.Loading -> CircularProgressIndicator()
                            is AsyncImagePainter.State.Success -> SubcomposeAsyncImageContent()
                        }
                    }
                }
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(CenterHorizontally),
                )
            }
        }
    }
}
