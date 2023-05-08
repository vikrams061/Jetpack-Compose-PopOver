package com.layout.view.popui.compose

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "Light", uiMode = UI_MODE_NIGHT_NO)
@Preview(name = "Night", uiMode = UI_MODE_NIGHT_YES)
annotation class ThemePreview
