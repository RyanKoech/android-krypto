package com.ryankoech.krypto.feature_transaction.presentation.choose_asset.components.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.components.loading.CoinCardLoading
import com.ryankoech.krypto.common.presentation.components.loading.LoadingText
import com.ryankoech.krypto.common.presentation.components.loading.loadingEffect
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.presentation.util.KryptoPreview

@Composable
fun ChooseAssetItemLoading(
    brush : Brush,
    modifier: Modifier = Modifier,
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(
            modifier = Modifier
                .size(56.dp)
                .background(
                    brush = brush,
                    shape = MaterialTheme.shapes.small
                )
        )

        Spacer(
            modifier = Modifier
            .width(16.dp)
        )

        LoadingText(130.dp, brush)

    }

}

@KryptoPreview
@Composable
fun ChooseAssetItemLoadingPreview(){
    KryptoTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val brush = loadingEffect()

            Column{
                ChooseAssetItemLoading(brush = brush)
            }

        }
    }
}