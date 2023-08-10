package com.ryankoech.krypto.feature_settings.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.presentation.util.KryptoPreview
import timber.log.Timber

@Composable
fun AboutItem(
    modifier: Modifier = Modifier,
    title : String,
    details : String,
    onClick : (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h1.copy(
                fontSize = 24.sp
            ),
        )

        Spacer(modifier = Modifier.height(8.dp))

        var textModifier : Modifier = Modifier
        onClick?.apply {
            textModifier = textModifier.then(
                Modifier.clickable {
                    onClick()
                }
            )
        }

        Text(
            modifier = textModifier,
            text = details,
            style = MaterialTheme.typography.body1.copy(
                fontSize = 18.sp
            ),
        )
    }
}

@KryptoPreview
@Composable
fun AboutItemPreview() {
    KryptoTheme {
        Surface {
            AboutItem(Modifier, "Krypto", "Version 1.0.0")
        }
    }
}