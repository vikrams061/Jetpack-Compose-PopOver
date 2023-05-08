package com.layout.view.popui.compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.layout.view.popui.compose.theme.SkylabTheme

abstract class ComposeFragment : Fragment() {

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = contentView(
        context = requireContext(),
        compositionStrategy = ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner),
        content = {
            SkylabTheme {
                this.Content()
            }
        }
    )

    @Composable
    protected abstract fun Content()
}
