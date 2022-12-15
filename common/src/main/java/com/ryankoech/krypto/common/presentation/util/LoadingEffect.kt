package com.ryankoech.krypto.common.presentation.components.loading

import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Brush.Companion.horizontalGradient
import androidx.compose.ui.graphics.Color

@Composable
fun loadingEffect(): Brush {

    val transition = rememberInfiniteTransition() // animate infinite times

    val translateAnimation = transition.animateFloat( //animate the transition
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 3000, // duration for the animation
                easing = EaseInOutCubic
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    val colorStops = arrayOf(
        0.0f to Color.LightGray.copy(alpha = 0.9f),
        translateAnimation.value to Color.LightGray.copy(alpha = 0.3f),
        1f to Color.LightGray.copy(alpha = 0.9f)
    )

    return horizontalGradient(
        colorStops = colorStops,
        startX = 0f,
        endX = Float.POSITIVE_INFINITY,
    )
}