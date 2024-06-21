package com.namkuzo.feriaspot.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun OutlineBox(
    modifier: Modifier = Modifier,
    colorBackground: Color = MaterialTheme.colorScheme.background,
    borderColor: Color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
    borderWidth: Dp = 2.dp,
    cornerShapeSize: Dp = 28.dp,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier.fillMaxWidth().clickable { onClick() },
        color = colorBackground,
        shape = RoundedCornerShape(cornerShapeSize),
        border = BorderStroke(borderWidth, borderColor)
    ) {
        content()
    }
}
