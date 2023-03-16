package com.ryankoech.krypto.feature_settings.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.feature_settings.R

@Composable
fun SettingsItem(
    modifier : Modifier = Modifier,
    onClick : () -> Unit,
    @DrawableRes iconRes : Int,
    title : String,
    details : String,
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 8.dp
            )
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier
                .size(32.dp),
            painter = painterResource(iconRes),
            contentDescription = title,
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                modifier = Modifier
                    .padding(
                        bottom = 4.dp
                    ),
                text = title,
                style = MaterialTheme.typography.h2
            )
            
            Text(
                text = details,
                style = MaterialTheme.typography.caption
            )
        }
    }

}

@Preview
@Composable
fun SettingsItemPreview() {

    KryptoTheme {
        Surface {
            SettingsItem(
                Modifier, {}, R.drawable.icon_info, "App Info", "Learn more about Krypto"
            )
        }
    }

}