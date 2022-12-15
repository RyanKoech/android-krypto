package com.ryankoech.krypto.common.presentation.components.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LoadingText(
    width : Dp,
    brush : Brush,
    modifier: Modifier = Modifier
) {

    Spacer(
        modifier = modifier
            .height(16.dp)
            .width(width)
            .background(
                brush = brush,
                shape = RoundedCornerShape(4.dp)
            )
    )
}