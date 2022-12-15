package com.ryankoech.krypto.common.presentation.components.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme

@Composable
fun CoinCardLoading(
    brush : Brush,
    modifier: Modifier = Modifier,
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
    ) {
        Spacer(
            modifier = Modifier
                .size(56.dp)
                .background(
                    brush = brush,
                    shape = MaterialTheme.shapes.medium
                ),
        )

        Spacer(
            modifier = Modifier
                .width(10.dp)
        )

        Column(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxHeight()
                .weight(1.0f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                LoadingText(130.dp, brush)
                LoadingText(70.dp, brush)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                LoadingText(100.dp, brush)
                LoadingText(50.dp, brush)
            }
        }
    }


}

@Preview
@Composable
fun CoinCardLoadingPreview() {

    KryptoTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val brush = loadingEffect()

            Column{
                CoinCardLoading(brush = brush)
            }

        }
    }

}