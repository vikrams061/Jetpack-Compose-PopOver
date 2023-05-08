package com.layout.view.popui.compose.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.layout.popover.R
import com.layout.view.popui.compose.theme.SkylabTheme

@Composable
fun ProfileAppBar(
    userName: String,
    handle: String,
    onNavigationIconClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = SkylabTheme.colors.primaryColors.background,
) = TopAppBar(
    backgroundColor = backgroundColor,
    modifier = modifier,
    content = {
        IconButton(
            onClick = onNavigationIconClick,
            modifier = Modifier
                .size(28.dp),
            content = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = com.google.android.material.R.drawable.abc_text_select_handle_left_mtrl),
                    contentDescription = "navigationIcon",
                    modifier = Modifier.fillMaxSize(),
                    tint = SkylabTheme.colors.iconColors.default
                )
            }
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = userName,
                color = SkylabTheme.colors.primaryColors.alt,
                maxLines = 1,
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                ),
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(0.dp)
                    .wrapContentHeight()
            )
            Text(
                text = handle,
                maxLines = 1,
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                ),
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(0.dp)
                    .wrapContentHeight()
            )
        }
    }
)