package com.layout.view.popui.compose.utils.tag

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString

data class TagEvent(
    val tag: Tag,
    val value: String
)

object TagHelper {

    private val ADDITIONAL_CHARS: Set<Char> = hashSetOf('_', '.')
    private const val LAST_REGEX = "%s[a-zA-Z0-9_.]+?(?![a-zA-Z0-9_.])$"

    @JvmStatic
    fun findLastTag(
        text: String,
        vararg tags: Tag
    ): TagEvent? {
        require(tags.isNotEmpty()) { "Tags must not be empty" }
        val mTags: Set<Tag> = tags.toSet()

        return mTags.mapNotNull { tag ->
            val regex = String.format(LAST_REGEX, tag.sign).toRegex()
            val eventValue = regex.find(text)?.value ?: return@mapNotNull null
            TagEvent(tag, eventValue)
        }.firstOrNull()
    }

    @JvmStatic
    fun buildAnnotatedStringWithColors(
        tags: Map<Char, Tag>,
        text: String,
    ): AnnotatedString {
        var index = 0
        return buildAnnotatedString {
            append(text)

            while (index in text.indices) {
                val sign: Char = text[index]

                index = tags[sign]?.let { tag ->
                    val finishIndex = findNextValidHashTagChar(text, index)
                    if (finishIndex - index > 1) // ignore when typing has only sign
                        addStyle(
                            style = SpanStyle(
                                color = tag.color,
                            ),
                            start = index,
                            end = finishIndex
                        )
                    finishIndex
                } ?: (index + 1)
            }
        }
    }

    @JvmStatic
    private fun findNextValidHashTagChar(text: CharSequence, start: Int): Int {
        var nonLetterDigitCharIndex = -1 // skip first sign '#"

        for (index in start + 1 until text.length) {
            val sign = text[index]
            val isValidSign = Character.isLetterOrDigit(sign) || ADDITIONAL_CHARS.contains(sign)
            if (!isValidSign) {
                nonLetterDigitCharIndex = index
                break
            }
        }

        if (nonLetterDigitCharIndex == -1) nonLetterDigitCharIndex = text.length
        return nonLetterDigitCharIndex
    }
}
