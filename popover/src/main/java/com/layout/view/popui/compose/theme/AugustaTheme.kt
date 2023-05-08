package com.layout.view.popui.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import app.skylab.compose.colors.LocalColors
import app.skylab.compose.colors.SkylabColors
import app.skylab.compose.colors.defaultDarkColors
import app.skylab.compose.colors.defaultLightColors

@Composable
fun SkylabTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) = AugustaTheme(
    colors = if (isDarkTheme) defaultDarkColors() else defaultLightColors(),
    content = content
)

@Composable
private fun AugustaTheme(
    colors: SkylabColors = SkylabTheme.colors,
    typography: SkylabTypography = SkylabTypography(),
    content: @Composable () -> Unit,
) = CompositionLocalProvider(
    LocalColors provides colors,
    content = content
)

object SkylabTheme {

    /**
     * These represent the default ease-of-use accessors for colors, typography.
     * */
    val colors: SkylabColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current


}
