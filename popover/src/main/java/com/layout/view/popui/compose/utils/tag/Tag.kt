package com.layout.view.popui.compose.utils.tag

import androidx.compose.ui.graphics.Color

interface Tag {
    val sign: Char
    val color: Color
}

object HashTag : Tag {
    override val sign: Char = '#'
    override val color: Color = Color(0xFF006BD6)
}

object MentionTag : Tag {
    override val sign: Char = '@'
    override val color: Color = Color(0xFF006BD6)
}
