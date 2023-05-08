package com.layout.view.popui.compose

import android.content.Context
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.savedstate.SavedStateRegistryOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner

internal fun Fragment.contentView(
    context: Context,
    compositionStrategy: ViewCompositionStrategy = ViewCompositionStrategy.DisposeOnDetachedFromWindow,
    content: @Composable () -> Unit,
): ComposeView {

    val composeView = ComposeView(context).apply {
        setViewCompositionStrategy(compositionStrategy)
        setContent(content)

        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    composeView.setViewTreeLifecycleOwner(viewLifecycleOwner)
    composeView.setViewTreeSavedStateRegistryOwner(this as SavedStateRegistryOwner)

    return composeView
}
