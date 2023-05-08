package com.layout.view.popui.compose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.layout.view.popui.compose.theme.SkylabTheme

@Composable
fun LoadingWidget(
    modifier: Modifier = Modifier
) = Box(
    modifier = modifier
) {
    Box(
        modifier = Modifier
            .size(72.dp)
            .align(Alignment.Center)
            .background(color = Color.White.copy(alpha = 0.5f))
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@Preview
@Composable
private fun ComponentPreview() {
    SkylabTheme {
        LoadingWidget(
            modifier = Modifier.fillMaxSize()
        )
    }
}