package com.ryankoech.krypto.feature_settings.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.feature_settings.R
import com.ryankoech.krypto.feature_settings.core.ktx.rateInPlaystore
import com.ryankoech.krypto.feature_settings.core.ktx.sendMail
import com.ryankoech.krypto.feature_settings.core.ktx.sendMessage
import com.ryankoech.krypto.feature_settings.presentation.components.SettingsItem
import com.ryankoech.krypto.feature_settings.presentation.util.SettingsEntity

@Composable
fun SettingsScreen(
    modifier : Modifier = Modifier,
    navigateToAboutScreen : () -> Unit,
) {

    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current

    val settingsList = listOf(
        SettingsEntity(
            title = "App Info",
            details = "Learn more about Krypto",
            iconRes = R.drawable.icon_info,
            action = navigateToAboutScreen
        ),
        SettingsEntity(
            title = "Report An Issue",
            details = "Tell us what wrong with the app",
            iconRes = R.drawable.icon_bug,
            action = {
                context.sendMail(
                    to = "sirryankoech@gmail.com",
                    subject = "Krypto - Report a Problem",
                    body = "Device Information:\n\nYour Issue:"
                )
            }
        ),
        SettingsEntity(
            title = "Share Krypto",
            details = "Help other people discover the app",
            iconRes = R.drawable.icon_share,
            action = {
                context.sendMessage(
                    message = "A simple way to manager crypto assets and practice crypto trading. By Ryan and the android community.\n\nRelease coming soon.",
                )
            }
        ),
        SettingsEntity(
            title = "Rate Our App",
            details = "Leave a review if you loved Krypto",
            iconRes = R.drawable.icon_star,
            action = {
                context.rateInPlaystore()
            }
        ),
        SettingsEntity(
            title = "Buy Me Coffee",
            details = "Help support further development",
            iconRes = R.drawable.icon_gift,
            action = {
                uriHandler.openUri("https://www.buymeacoffee.com/RyanKoech")
            }
        ),
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(
            12.dp
        )
    ){
        items(settingsList){ settingListItem ->
            SettingsItem(
                onClick = settingListItem.action, iconRes = settingListItem.iconRes, title = settingListItem.title, details = settingListItem.details
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }

}

@Preview
@Composable
fun SettingsScreenPreview() {
    KryptoTheme{
        Surface {
            SettingsScreen(Modifier, {})
        }
    }
}