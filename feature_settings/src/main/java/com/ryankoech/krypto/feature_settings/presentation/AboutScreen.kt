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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.feature_settings.R
import com.ryankoech.krypto.feature_settings.core.ktx.sendMail
import com.ryankoech.krypto.feature_settings.presentation.components.AboutItem
import com.ryankoech.krypto.feature_settings.presentation.util.AboutEntity
import com.ryankoech.krypto.feature_settings.presentation.util.EMAIL_RYAN

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current


    val aboutList = listOf(
        AboutEntity(
            title = "Krypto",
            details = stringResource(R.string.about_app_details, "1.0.0")
        ),
        AboutEntity(
            title = stringResource(R.string.about_developer_title),
            details = stringResource(R.string.about_developer_details),
            action = {
                uriHandler.openUri("https://github.com/RyanKoech/android-krypto")
            }
        ),
        AboutEntity(
            title = stringResource(R.string.about_contact_title),
            details = EMAIL_RYAN,
            action = {
                context.sendMail(
                    to = EMAIL_RYAN,
                    subject = "Inquiry From Krypto",
                    body = "Hey Ryan,\n",
                )
            }
        ),
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

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