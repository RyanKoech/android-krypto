package com.ryankoech.krypto.feature_settings.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme

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

        Text(
            modifier = Modifier
                .apply TextModifier@{
                    val textModifier = this
                    onClick?.apply {
                        textModifier.clickable {
                            onClick()
                        }
                    }
                },
            text = details,
            style = MaterialTheme.typography.body1.copy(
                fontSize = 18.sp
            ),
        )
    }
}

@Preview
@Composable
fun AboutItemPreview() {
    KryptoTheme {
        Surface {
            AboutItem(Modifier, "Krypto", "Version 1.0.0")
        }
    }
}