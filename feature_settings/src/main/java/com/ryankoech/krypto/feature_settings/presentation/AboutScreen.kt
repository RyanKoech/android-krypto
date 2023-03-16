package com.ryankoech.krypto.feature_settings.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.feature_settings.core.ktx.sendMail
import com.ryankoech.krypto.feature_settings.presentation.components.AboutItem
import com.ryankoech.krypto.feature_settings.presentation.util.AboutEntity

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current


    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        val aboutList = listOf(
            AboutEntity(
                title = "Krypto",
                details ="Version 1.0.0"
            ),
            AboutEntity(
                title = "Developed by",
                details = "Ryan Koech and community",
                action = {
                    uriHandler.openUri("https://github.com/RyanKoech/android-krypto")
                }
            ),
            AboutEntity(
                title = "Contact Me",
                details = "sirryankoech@gmail.com",
                action = {
                    context.sendMail(
                        to = "sirryankoech@gmail.com",
                        subject = "Inquiry From Krypto",
                        body = "Hey Ryan,\n",
                    )
                }
            ),
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