package com.layout.popover.readmore

import androidx.compose.runtime.Stable

@JvmInline
public value class ReadMoreTextOverflow private constructor(internal val value: Int) {

    override fun toString(): String {
        return when (this) {
            Clip -> "Clip"
            Ellipsis -> "Ellipsis"
            else -> "Invalid"
        }
    }

    public companion object {
        /**
         * Clip the overflowing text to fix its container.
         */
        @Stable
        public val Clip: ReadMoreTextOverflow = ReadMoreTextOverflow(1)

        /**
         * Use an ellipsis to indicate that the text has overflowed.
         */
        @Stable
        public val Ellipsis: ReadMoreTextOverflow = ReadMoreTextOverflow(2)
    }
}
