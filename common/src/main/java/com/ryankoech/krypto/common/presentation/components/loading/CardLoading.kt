package com.ryankoech.krypto.common.presentation.components.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.presentation.util.KryptoPreview

@Composable
fun CardLoading(
    brush: Brush,
    modifier: Modifier = Modifier,
){

    Spacer(
        modifier = modifier
            .fillMaxWidth()
            .height(LocalConfiguration.current.screenHeightDp.dp / 4)
            .background(
                brush = brush,
                shape = MaterialTheme.shapes.large
            )
    )

}

@KryptoPreview
@Composable
fun CardLoadingPreview(){

    KryptoTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            val brush = loadingEffect()

            Column{ CardLoading(brush) }
        }
    }

}