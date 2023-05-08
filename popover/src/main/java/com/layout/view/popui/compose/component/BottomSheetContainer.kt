package com.layout.view.popui.compose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.layout.view.popui.compose.theme.SkylabTheme

@Composable
fun BottomSheetContainer(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) = Surface(
    modifier = modifier,
    color = Color.White,
    elevation = 4.dp,
) {
    Column(
        modifier = Modifier
    ) {
        Spacer(
            modifier = Modifier
                .padding(12.dp)
                .height(6.dp)
                .width(120.dp)
                .align(Alignment.CenterHorizontally)
                .background(
                    color = SkylabTheme.colors.borderColors.active.copy(alpha = 0.2f),
                    shape = CircleShape
                )
        )

        content()
    }
}

