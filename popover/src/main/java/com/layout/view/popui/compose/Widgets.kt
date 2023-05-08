package com.layout.view.popui.compose

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.layout.view.popui.compose.theme.SkylabTheme
import com.layout.view.popui.compose.utils.SymbolAnnotationType
import com.layout.view.popui.compose.utils.messageFormatter
import com.layout.view.popui.compose.utils.tag.HashTag

@Composable
fun Divider(
    modifier: Modifier = Modifier
) {
    Spacer(
        modifier = modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(
                SkylabTheme.colors.borderColors.active.copy(alpha = 0.2f)
            )
    )
}
val focusRequester = FocusRequester()
@Composable
fun PostText(
    linkUrl:String?="",
    linkDesc:String?="",
    linkTitle:String?="",
    modifier: Modifier = Modifier,
    text: String,
    color: Color = SkylabTheme.colors.primaryColors.alt,
    maxLines: Int = 3,
    showMoreText: AnnotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = HashTag.color)) {
            append("Read more")
        }
    },
    showLessText: AnnotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = HashTag.color)) {
            append("Show less")
        }
    },
    onHashtagPressed: (String) -> Unit,
    onMentionPressed: (String) -> Unit,
    onPostPressed: () -> Unit,
    onLinkPressed: (String) -> Unit,
) {
    Column {
        var expanded by remember { mutableStateOf(true) }
        var tooMuchText by remember { mutableStateOf(false) }
        val textLayoutResultState = remember { mutableStateOf<TextLayoutResult?>(null) }
        var currentMaxLines by remember { mutableStateOf(maxLines) }
        val styledMessage = messageFormatter(
            text = text,
            color = color,
            primary = true,
        )
        ClickableText(
            text = styledMessage,
            maxLines = currentMaxLines,
            overflow = TextOverflow.Clip,
            modifier = modifier
                .animateContentSize()
                .fillMaxWidth()
                .padding(start = 24.dp, end = 16.dp, top=2.dp, bottom = 2.dp ),
            onTextLayout = { textLayoutResult ->
                textLayoutResultState.value = textLayoutResult
                expanded = !textLayoutResult.hasVisualOverflow
            },
            onClick = { position ->
                styledMessage.getStringAnnotations(position, position)
                    .firstOrNull().let { annotation ->
                        when (annotation?.tag) {
                            SymbolAnnotationType.LINK.name -> {
                                onLinkPressed(annotation.item)
                            }

                            SymbolAnnotationType.PERSON.name -> {
                                onMentionPressed("@" + annotation.item)
                            }

                            SymbolAnnotationType.HASHTAG.name -> {
                                onHashtagPressed(annotation.item)
                            }

                            else -> {
                                onPostPressed()
                            }
                        }
                    }
            }
        )
        if (tooMuchText) {
            ClickableText(
                modifier = Modifier.padding(horizontal = 16.dp)
                .align(Alignment.End),
                text = if (expanded) showLessText else showMoreText,
                maxLines=1,
                onClick = {
                    currentMaxLines = if (expanded) maxLines else Int.MAX_VALUE
                }
            )
        }

            if (linkUrl != "" && linkUrl != null) {
                Card(
                    backgroundColor = Color.White,
                    modifier = modifier
                        .padding(10.dp)
                        .clickable { onLinkPressed(linkUrl) },
                    elevation = 8.dp,
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center
                    ) {
                    }
                }
            }
        LaunchedEffect(true) {
            tooMuchText = textLayoutResultState.value?.hasVisualOverflow == true
        }
    }
}

@Composable
fun PostTextDetails(
    isList: Boolean,
    modifier: Modifier = Modifier,
    text: String,
    postId: String,
    color: Color = Color.Black,
    maxLines: Int = 3,
    showMoreText: AnnotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = HashTag.color)) {
            append("Read more")
        }
    },
    showLessText: AnnotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = HashTag.color)) {
            append("Show less")
        }
    },
    onHashtagPressed: (String) -> Unit,
    onMentionPressed: (String) -> Unit,
    onPostPressed: () -> Unit,
    onPostDetails: (String) -> Unit,
) {
    Column {
        var expanded by remember { mutableStateOf(true) }
        var tooMuchText by remember { mutableStateOf(false) }
        val textLayoutResultState = remember { mutableStateOf<TextLayoutResult?>(null) }
        var currentMaxLines by remember { mutableStateOf(maxLines) }
        val styledMessage = messageFormatter(
            text = text,
            color = color,
            primary = true,
        )

        ClickableText(
            text = styledMessage,
            maxLines = currentMaxLines,
            overflow = TextOverflow.Clip,
            modifier = modifier
                .animateContentSize()
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            onTextLayout = { textLayoutResult ->
                textLayoutResultState.value = textLayoutResult
                expanded = !textLayoutResult.hasVisualOverflow
            },
            onClick = { position ->
                styledMessage.getStringAnnotations(position, position)
                    .firstOrNull().let { annotation ->
                        when (annotation?.tag) {
                            SymbolAnnotationType.LINK.name -> {
                            }

                            SymbolAnnotationType.PERSON.name -> {
                                onMentionPressed("@" + annotation.item)
                            }

                            SymbolAnnotationType.HASHTAG.name -> {
                                onHashtagPressed(annotation.item)
                            }

                            else -> onPostPressed()
                        }
                    }
            }
        )

        if (isList) {
            tooMuchText = textLayoutResultState.value?.hasVisualOverflow == true
        }

        if (tooMuchText) {
            ClickableText(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                text = if (isList) {
                    showMoreText
                } else {
                    if (expanded) showLessText else showMoreText
                },
                onClick = {
                    if (isList) {
                        onPostDetails.invoke(postId)
                    } else {
                        currentMaxLines = if (expanded) maxLines else Int.MAX_VALUE
                    }
                }
            )
        }
        if (!isList) {
            LaunchedEffect(true) {
                tooMuchText = textLayoutResultState.value?.hasVisualOverflow == true
            }
        }
    }
}
