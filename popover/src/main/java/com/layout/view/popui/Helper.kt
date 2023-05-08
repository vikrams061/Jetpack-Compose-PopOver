package com.layout.view.popui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.layout.view.popui.compose.utils.SymbolAnnotationType
import com.layout.view.popui.compose.utils.messageFormatter
import com.layout.view.popui.compose.utils.tag.HashTag
import com.layout.view.popui.compose.theme.SkylabTheme

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
