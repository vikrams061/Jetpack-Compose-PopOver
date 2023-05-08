package com.layout.view.popui.compose

import android.os.SystemClock
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.core.graphics.ColorUtils
import androidx.compose.ui.graphics.Color as ComposeColor

fun LazyListState.isScrolledToTheEnd() =
    layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1

fun LazyListState.isScrolledToTheStart() =
    layoutInfo.visibleItemsInfo.lastOrNull()?.index == 0

/**
 * Wraps an [onClick] lambda with another one that supports debouncing. The default deboucing time
 * is 1000ms.
 *
 * @return debounced onClick
 */
@Composable
inline fun debounced(crossinline onClick: () -> Unit, debounceTime: Long = 1000L): () -> Unit {
    var lastTimeClicked by remember { mutableStateOf(0L) }
    val onClickLambda: () -> Unit = {
        val now = SystemClock.uptimeMillis()
        if (now - lastTimeClicked > debounceTime) {
            onClick()
        }
        lastTimeClicked = now
    }
    return onClickLambda
}

/**
 * The same as [Modifier.clickable] with support to debouncing.
 */
fun Modifier.debouncedClickable(
    debounceTime: Long = 1000L,
    onClick: () -> Unit
): Modifier {
    return this.composed {
        val clickable = debounced(debounceTime = debounceTime, onClick = { onClick() })
        this.clickable { clickable() }
    }
}

fun ComposeColor.invert(): ComposeColor {
    val hsl = floatArrayOf(0f, 0f, 0f)
    ColorUtils.colorToHSL((value shr 32).toInt(), hsl)
    hsl[2] = 1 - hsl[2]
    val colorInt = ColorUtils.HSLToColor(hsl)
    return ComposeColor(colorInt)
}

fun Modifier.conditional(
    predicate: Boolean,
    other: Modifier.() -> Modifier
): Modifier =
    if (predicate) {
        this.then(other())
    } else {
        this
    }

/**
 * The same as [Modifier.clickable] with support to ripple.
 */
fun Modifier.rippleClickable(
    enabled: Boolean = true,
    role: Role = Role.Button,
    onClickLabel: String? = null,
    onClick: () -> Unit
): Modifier {
    return this.composed {
        this.clickable(
            role = role,
            enabled = enabled,
            onClick = onClick,
            onClickLabel = onClickLabel,
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple()
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.rippleCombinedClickable(
    enabled: Boolean = true,
    role: Role = Role.Button,
    onClickLabel: String? = null,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
): Modifier {
    return this.composed {
        this.combinedClickable(
            role = role,
            enabled = enabled,
            onClick = onClick,
            onLongClick = onLongClick,
            onClickLabel = onClickLabel,
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(),
        )
    }
}

fun Modifier.rippleClickable(
    enabled: Boolean = true,
    role: Role = Role.Button,
    onClickLabel: String? = null,
    indication: Indication,
    onClick: () -> Unit
): Modifier {
    return this.composed {
        this.clickable(
            role = role,
            enabled = enabled,
            onClick = onClick,
            onClickLabel = onClickLabel,
            interactionSource = remember { MutableInteractionSource() },
            indication = indication
        )
    }
}

fun TextFieldValue.currentMention(): String? {
    val regex = "@[a-zA-Z\\d][a-zA-Z\\d_-]{1,}$".toRegex()
    val leftPart = text.substring(0, selection.start)
    if (leftPart.contains("@")) {
        return regex.find(leftPart)?.value?.substring(1)
    }
    return null
}

fun TextFieldValue.withAtMention(): String? {
//    val handlePattern = Regex("""@([A-Za-z0-9_-]+)""")
    val regex = "@[a-zA-Z\\d][a-zA-Z\\d_-]{2,}".toRegex()
    val leftPart = text.substring(0, selection.start)
    val result = leftPart.split(" ")
    if (result.last().contains("@")) {
//        if(result.last().toString().length>0){
//            return ""
//        }
        return result.last().toString()
    }
    return null
}

fun TextFieldValue.withUpdatedMention(mention: String): TextFieldValue {
    try {
        val lastMentionStartBeforeCursor = text.substring(0, selection.start).lastIndexOf('@')
        val regex = "@[a-zA-Z\\d][a-zA-Z\\d_-]{1,}".toRegex()
        val range = regex.find(text, lastMentionStartBeforeCursor)?.range
        if (range != null && range.last + 1 >= selection.start) {
            val newText = StringBuilder()
                .append(text.substring(0, range.first))
                .append("@$mention")
                .append(text.substring(range.last + 1))
                .toString()
            return copy(text = newText, selection = TextRange(range.first + mention.length + 1))
        }
    }catch (e: Exception){e.printStackTrace()}

    return this
}

fun TextFieldValue.withInsertedText(insertion: String): TextFieldValue {
    val (start, end) = if (selection.reversed) {
        Pair(selection.end, selection.start)
    } else {
        Pair(selection.start, selection.end)
    }
    val newText = StringBuilder()
        .append(text.substring(0, start))
        .append(insertion)
        .append(text.substring(end))
        .toString()
    return copy(
        text = newText,
        selection = TextRange(start + insertion.length)
    )
}
