package com.layout.view.popui.compose.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout

private enum class MessageLayoutContent {
    Top,
    Content,
    Reaction,
    Bottom
}

@Composable
fun MessageLayout(
    top: @Composable BoxScope.() -> Unit,
    content: @Composable () -> Unit,
    bottom: @Composable BoxScope.() -> Unit,
    reaction: @Composable BoxScope.() -> Unit,
    color: Color = Color.Transparent,
    contentColor: Color = Color.Transparent,
    modifier: Modifier
) {
    Surface(modifier = modifier, color = color, contentColor = contentColor) {
        MessageLayout(
            top = {
                Box(content = top)
            },
            content = content,
            bottom = {
                Box(content = bottom)
            },
            reaction = {
                Box(content = reaction)
            },
        )
    }
}

@Composable
private fun MessageLayout(
    top: @Composable () -> Unit,
    content: @Composable () -> Unit,
    bottom: @Composable () -> Unit,
    reaction: @Composable () -> Unit,
) {
    SubcomposeLayout { constraints ->
        val layoutWidth = constraints.maxWidth

        val looseConstraints = constraints.copy(minWidth = layoutWidth, minHeight = 0)

        // // TOP START
        val topPlaceables = subcompose(MessageLayoutContent.Top, top).map { it.measure(looseConstraints) }
        val topHeight = topPlaceables.maxBy { it.height }.height

        // // TOP END
        // // REACTION START
        val reactionPlaceables = subcompose(MessageLayoutContent.Reaction, reaction).map { it.measure(looseConstraints) }
        val reactionHeight = reactionPlaceables.maxBy { it.height }.height
        val middleOfReactionHeight = reactionHeight / 2

        // // BODY START
        val bottomPlaceables = subcompose(MessageLayoutContent.Bottom, bottom).map { it.measure(looseConstraints) }
        val bottomHeight = bottomPlaceables.maxBy { it.height }.height

        val contentPlaceables = subcompose(MessageLayoutContent.Content, content).map { it.measure(looseConstraints) }
        val contentHeight = contentPlaceables.maxBy { it.height }.height
        val contentWidth = contentPlaceables.maxBy { it.width }.width
        // // BOTTOM END

        layout(contentWidth, (topHeight + contentHeight + bottomHeight.coerceAtLeast(middleOfReactionHeight))) {
            topPlaceables.forEach { it.place(0, 0) }
            contentPlaceables.forEach { it.place(0, topHeight) }
            bottomPlaceables.forEach { it.place(0, topHeight + contentHeight) }
            reactionPlaceables.forEach { it.place(0, topHeight + contentHeight - middleOfReactionHeight) }
        }
    }
}
