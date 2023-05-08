package com.layout.view.popui.compose.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.layout.view.popui.compose.rippleCombinedClickable

private val IMAGES_SPACE = 2.dp
private const val MAX_VISIBLE_ITEMS = 6
private const val MAX_ITEMS_PER_ROW = 2

@Composable
private fun AsyncImageVideoComponent(
    model: Any?,
    isEdit: Boolean,
    mediaType: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    imageShape: Shape,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.FillBounds,
    padding: Int,
    visibleHeader: Boolean,
    onEditPressed: (Any, Any) -> Unit,
    onRemovePressed: (Any) -> Unit
) = Box(
    modifier = modifier
        .fillMaxHeight(1f)
        .defaultMinSize(minHeight = 200.dp)
) {

}

@Composable
internal fun ImageFlexBox(
    models: List<List<Any>>,
    isEdit: List<Boolean>,
    mediaType: List<String>,
    visibleHeader: Boolean,
    imageShape: Shape,
    onEditPressed: (Any, Any) -> Unit,
    onRemovePressed: (Any) -> Unit,
    onMediaClick: (rowIndex: Int, columnIndex: Int) -> Unit,
    onLongClick: () -> Unit
) = Column(
    modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(IMAGES_SPACE)
) {
    val padding = 0
    models.forEachIndexed { rowIndex, modelsRow ->
        Row(
            modifier = Modifier.fillMaxWidth(1f),
            horizontalArrangement = Arrangement.spacedBy(IMAGES_SPACE)
        ) {
            modelsRow.forEachIndexed { columnIndex, model ->
                AsyncImageVideoComponent(
                    model = model,
                    isEdit = isEdit[columnIndex],
                    mediaType = mediaType[columnIndex],
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    imageShape = imageShape,
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .rippleCombinedClickable(
                            onLongClick = onLongClick,
                            onClick = { onMediaClick(rowIndex, columnIndex) }
                        ),
                    padding = padding,
                    visibleHeader = visibleHeader,
                    onEditPressed = onEditPressed,
                    onRemovePressed = onRemovePressed
                )
            }
        }
    }
}

@Composable
internal fun ImageFlexBox1(
    models: List<List<Any>>,
    isEdit: List<Boolean>,
    mediaType: List<String>,
    visibleHeader: Boolean,
    imageShape: Shape,
    onEditPressed: (Any, Any) -> Unit,
    onRemovePressed: (Any) -> Unit,
    onMediaClick: (rowIndex: Int, columnIndex: Int) -> Unit,
    onLongClick: () -> Unit
) = Column(
    modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.spacedBy(IMAGES_SPACE)
) {
    val padding = 0
    models.forEachIndexed { rowIndex, modelsRow ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(IMAGES_SPACE)
        ) {
            modelsRow.forEachIndexed { columnIndex, model ->
                if (mediaType[0] == "VIDEO") {
                    AsyncImageVideoComponent(
                        model = model,
                        isEdit = isEdit[columnIndex],
                        mediaType = mediaType[columnIndex],
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        imageShape = imageShape,
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f, true)
                            .rippleCombinedClickable(
                                onLongClick = onLongClick,
                                onClick = { onMediaClick(rowIndex, columnIndex) }
                            ),
                        padding = padding,
                        visibleHeader = visibleHeader,
                        onEditPressed = onEditPressed,
                        onRemovePressed = onRemovePressed
                    )
                } else if (model.toString().contains("gif")) {
                    AsyncImageVideoComponent(
                        model = model,
                        isEdit = isEdit[columnIndex],
                        mediaType = mediaType[columnIndex],
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        imageShape = imageShape,
                        modifier = Modifier
                            .weight(1f)
                            .rippleCombinedClickable(
                                onLongClick = onLongClick,
                                onClick = { onMediaClick(rowIndex, columnIndex) }
                            ),
                        padding = padding,
                        visibleHeader = visibleHeader,
                        onEditPressed = onEditPressed,
                        onRemovePressed = onRemovePressed
                    )
                } else {
                    AsyncImageVideoComponent(
                        model = model,
                        isEdit = isEdit[columnIndex],
                        mediaType = mediaType[columnIndex],
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        imageShape = imageShape,
                        modifier = Modifier
                            .weight(1f, true)
                            .rippleCombinedClickable(
                                onLongClick = onLongClick,
                                onClick = { onMediaClick(rowIndex, columnIndex) }
                            ),
                        padding = padding,
                        visibleHeader = visibleHeader,
                        onEditPressed = onEditPressed,
                        onRemovePressed = onRemovePressed
                    )
                }
            }
        }
    }
}

@Composable
internal fun ImageFlexBox2(
    models: List<Any>,
    isEdit: List<Boolean>,
    mediaType: List<String>,
    visibleHeader: Boolean,
    imageShape: Shape,
    onEditPressed: (uri: Any, mediaType: Any) -> Unit,
    onRemovePressed: (Any) -> Unit,
    onMediaClick: (index: Int) -> Unit,
    onLongClick: () -> Unit
) = Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(IMAGES_SPACE)
) {
    val padding = 0
    models.forEachIndexed { index, model ->
        AsyncImageVideoComponent(
            model = model,
            isEdit = isEdit[index],
            mediaType = mediaType[index],
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            imageShape = imageShape,
            modifier = Modifier
                .weight(1f)
                .aspectRatio(.5f)
                .rippleCombinedClickable(
                    onLongClick = onLongClick, onClick = { onMediaClick(index) }
                ),
            padding = padding,
            visibleHeader = visibleHeader,
            onEditPressed = onEditPressed,
            onRemovePressed = onRemovePressed
        )
    }
}

@Composable
internal fun ImageFlexBox3(
    models: List<Any>,
    isEdit: List<Boolean>,
    mediaType: List<String>,
    visibleHeader: Boolean,
    imageShape: Shape,
    onEditPressed: (uri: Any, mediaType: Any) -> Unit,
    onRemovePressed: (Any) -> Unit,
    onMediaClick: (index: Int) -> Unit,
    onLongClick: () -> Unit
) = Row(
    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(IMAGES_SPACE)
) {
    val padding = 0
    AsyncImageVideoComponent(
        model = models[0],
        isEdit = isEdit[0],
        mediaType = mediaType[0],
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        imageShape = imageShape,
        modifier = Modifier
            .weight(1f)
            .aspectRatio(.5f)
            .rippleCombinedClickable(
                onLongClick = onLongClick, onClick = { onMediaClick(0) }
            ),
        padding = padding,
        visibleHeader = visibleHeader,
        onEditPressed = onEditPressed,
        onRemovePressed = onRemovePressed
    )

    Column(
        modifier = Modifier
            .weight(1f)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(IMAGES_SPACE)
    ) {
        AsyncImageVideoComponent(
            model = models[1],
            isEdit = isEdit[1],
            mediaType = mediaType[1],
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            imageShape = imageShape,
            modifier = Modifier
                .aspectRatio(1f)
                .rippleCombinedClickable(
                    onLongClick = onLongClick, onClick = { onMediaClick(1) }
                ),
            padding = padding,
            visibleHeader = visibleHeader,
            onEditPressed = onEditPressed,
            onRemovePressed = onRemovePressed
        )
        AsyncImageVideoComponent(
            model = models[2],
            isEdit = isEdit[2],
            mediaType = mediaType[2],
            contentDescription = null,
            contentScale = ContentScale.Crop,
            imageShape = imageShape,
            modifier = Modifier
                .aspectRatio(1f)
                .rippleCombinedClickable(
                    onLongClick = onLongClick, onClick = { onMediaClick(2) }
                ),
            padding = padding,
            visibleHeader = visibleHeader,
            onEditPressed = onEditPressed,
            onRemovePressed = onRemovePressed
        )
    }
}
