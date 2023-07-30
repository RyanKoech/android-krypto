package com.ryankoech.krypto.feature_home.presentation.components.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.components.loading.loadingEffect
import com.ryankoech.krypto.common.presentation.components.loading.LoadingText
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.presentation.util.KryptoPreview

@Composable
fun HomeScreenActionsLoading(
    brush : Brush,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HomeScreenActionItemLoading(brush)
        HomeScreenActionItemLoading(brush)
        HomeScreenActionItemLoading(brush)
    }

}

@Composable
fun HomeScreenActionItemLoading(
    brush: Brush,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {

        Spacer(
            modifier = Modifier
                .size(40.dp)
                .background(
                    brush = brush,
                    shape = MaterialTheme.shapes.small
                )
        )

        Spacer(modifier = Modifier.height(12.dp))

        LoadingText(50.dp, brush)

    }

}

@KryptoPreview
@Composable
fun HomeScreenActionsLoadingPreview() {

    KryptoTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {

            val brush = loadingEffect()

            Column {
                HomeScreenActionsLoading(brush = brush)
            }

        }
    }

}
