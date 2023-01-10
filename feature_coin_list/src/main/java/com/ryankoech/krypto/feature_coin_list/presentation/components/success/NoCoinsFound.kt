package com.ryankoech.krypto.feature_coin_list.presentation.components.success

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.components.GifImage
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.R

@Composable
fun NoCoinsFound(
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(modifier = Modifier.size(112.dp)) {
            GifImage(
                modifier = Modifier.fillMaxSize(),
                res = R.drawable.icon_gif_search
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "No coins to show.",
            style = MaterialTheme.typography.body1
        )

    }

}

@Preview
@Composable
fun NoCoinsFoundPreview() {
    KryptoTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            NoCoinsFound()
        }
    }
}