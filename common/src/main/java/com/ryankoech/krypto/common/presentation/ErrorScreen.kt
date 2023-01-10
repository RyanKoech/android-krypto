package com.ryankoech.krypto.common.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.components.GifImage
import com.ryankoech.krypto.common.presentation.components.KryptoButton

@Composable
fun ErrorScreen(
    modifier : Modifier = Modifier,
    onButtonClick : () -> Unit,
    messageText : String,
    buttonText : String,
    @DrawableRes res : Int,
) {

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(modifier = Modifier.size(40.dp)) {
            GifImage(
                modifier = Modifier.fillMaxSize(),
                res = res
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = messageText,
            style = MaterialTheme.typography.body1
        )

        Spacer(modifier = Modifier.height(24.dp))

        KryptoButton(
            text = buttonText,
            onClick = onButtonClick
        )

    }


}