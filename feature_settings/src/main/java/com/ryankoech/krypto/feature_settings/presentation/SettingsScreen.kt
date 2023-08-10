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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.presentation.util.KryptoPreview
import com.ryankoech.krypto.feature_settings.BuildConfig
import com.ryankoech.krypto.feature_settings.R
import com.ryankoech.krypto.feature_settings.core.ktx.rateInPlaystore
import com.ryankoech.krypto.feature_settings.core.ktx.sendMail
import com.ryankoech.krypto.feature_settings.core.ktx.sendMessage
import com.ryankoech.krypto.feature_settings.presentation.components.SettingsItem
import com.ryankoech.krypto.feature_settings.presentation.util.EMAIL_RYAN
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
            title = stringResource(R.string.setting_info_title),
            details = stringResource(R.string.setting_info_description),
            iconRes = R.drawable.icon_info,
            action = navigateToAboutScreen
        ),
        SettingsEntity(
            title = stringResource(R.string.setting_bug_report_title),
            details = stringResource(R.string.setting_bug_report_description),
            iconRes = R.drawable.icon_bug,
            action = {
                context.sendMail(
                    to = EMAIL_RYAN,
                    subject = "Krypto - Report a Problem (Version: ${BuildConfig.VERSION_NAME})",
                    body = "Device Information:\n\nYour Issue:"
                )
            }
        ),
        SettingsEntity(
            title = stringResource(R.string.setting_share_app_title),
            details = stringResource(R.string.setting_share_app_description),
            iconRes = R.drawable.icon_share,
            action = {
                context.sendMessage(
                    message = context.getString(R.string.setting_share_app_message),
                )
            }
        ),
        SettingsEntity(
            title = stringResource(R.string.setting_rate_app_title),
            details = stringResource(R.string.setting_rate_app_description),
            iconRes = R.drawable.icon_star,
            action = {
                context.rateInPlaystore()
            }
        ),
        SettingsEntity(
            title = stringResource(R.string.setting_buy_coffee_title),
            details = stringResource(R.string.setting_buy_coffee_description),
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

@KryptoPreview
@Composable
fun SettingsScreenPreview() {
    KryptoTheme{
        Surface {
            SettingsScreen(Modifier, {})
        }
    }
}