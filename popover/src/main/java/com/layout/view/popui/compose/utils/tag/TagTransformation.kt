package com.layout.view.popui.compose.utils.tag

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import com.layout.view.popui.compose.utils.tag.TagHelper.buildAnnotatedStringWithColors

class TagTransformation(
    private val tags: Map<Char, Tag>
) : VisualTransformation {

    constructor(vararg tags: Tag) : this(tags.associateBy { it.sign })

    override fun filter(text: AnnotatedString): TransformedText = TransformedText(
        buildAnnotatedStringWithColors(tags, text.toString()),
        OffsetMapping.Identity
    )
}
