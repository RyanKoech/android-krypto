package com.ryankoech.krypto.feature_settings.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.feature_settings.presentation.components.AboutItem
import com.ryankoech.krypto.feature_settings.presentation.util.AboutEntity

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        val aboutList = listOf(
            AboutEntity("Krypto", "Version 1.0.0"),
            AboutEntity("Developed by", "Ryan Koech and community", {}),
            AboutEntity("Contact Me", "sirryankoech@gmail.com", {}),
        )

        items(aboutList){ aboutListItem ->
            AboutItem(
                title = aboutListItem.title,
                details = aboutListItem.details,
                onClick = aboutListItem.action
            )
            Spacer(modifier = Modifier.height(24.dp))
        }

    }

}

@Preview
@Composable
fun AboutScreenPreview() {
    KryptoTheme {
        Surface {
            AboutScreen()
        }
    }
}