package com.ryankoech.krypto.feature_home.presentation.components.success

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.theme.Green200
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.feature_home.presentation.theme.teaGreen200
import com.ryankoech.krypto.feature_home.presentation.theme.maroon50
import com.ryankoech.krypto.feature_home.R

@Composable
fun HomeScreenActions(
    onTransferInClick : () -> Unit,
    onTransferOutClick : () -> Unit,
    onWipeWalletClick : () -> Unit,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HomeScreenActionItem(
            onClick = onTransferInClick,
            text = stringResource(R.string.home_screen_actions_transfer_in),
            color = Green200,
            drawableResource = R.drawable.icon_download
        )
        HomeScreenActionItem(
            onClick = onWipeWalletClick,
            text = stringResource(R.string.home_screen_actions_wipe_wallet),
            color = maroon50,
            drawableResource = R.drawable.icon_delete
        )
        HomeScreenActionItem(
            onClick = onTransferOutClick,
            text = stringResource(R.string.home_screen_actions_transfer_out),
            color = teaGreen200,
            drawableResource = R.drawable.icon_upload
        )
    }

}

@Composable
fun HomeScreenActionItem(
    onClick : () -> Unit,
    text : String,
    @DrawableRes drawableResource : Int,
    color : Color,
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .background(
                    shape = MaterialTheme.shapes.small,
                    color = color
                )
                .clickable {
                    onClick()
                }
        ) {

            IconButton(
                modifier = Modifier
                    .padding(12.dp),
                onClick = onClick
            ) {
                    Icon(
                        modifier = Modifier
                            .size(28.dp),
                        painter = painterResource(drawableResource),
                        contentDescription = null
                    )
            }
        }
        
        Text(
            modifier = Modifier
                .padding(top = 12.dp),
            text = text,
            style = MaterialTheme.typography.body1,
        )

    }

}


@Preview
@Composable
fun HomeScreenActionsPreview() {
    KryptoTheme {
        Surface {
            HomeScreenActions({}, {}, {})
        }
    }
}

@Preview
@Composable
fun HomeScreenActionItemPreview() {

    KryptoTheme {
        Surface {
            HomeScreenActionItem(
                onClick = {},
                text = "Wipe Wallet",
                color = Color(0xffa1f5b9),
                drawableResource = R.drawable.icon_download
            )
        }
    }
}