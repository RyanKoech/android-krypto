package com.ryankoech.krypto.common.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import ke.co.sevenskies.common.R


@OptIn(ExperimentalCoilApi::class)
@Composable
fun CoinImage(url : String, name : String) {
    Box(
        modifier = Modifier
            .size(56.dp)
            .background(
                color = Color.Black,
                shape = RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center

    ) {

        val painter = rememberImagePainter(
            data = url,
            builder = {
                crossfade(200)
            }
        )

        Image(
            modifier = Modifier
                .padding(8.dp),
            painter = painter,
            contentDescription = stringResource(R.string.coin_image_content_description, name)
        )
    }
}
