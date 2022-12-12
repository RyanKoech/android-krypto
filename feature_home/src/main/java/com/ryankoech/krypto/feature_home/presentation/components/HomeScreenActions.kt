package com.ryankoech.krypto.feature_home.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ryankoech.krypto.common.presentation.theme.Green200
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import ke.co.sevenskies.feature_home.R

@Composable
fun HomeScreenActions() {

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HomeScreenActionItem(
            text = "Transfer In",
            color = Green200,
            drawableResource = R.drawable.icon_download
        ){}
        HomeScreenActionItem(
            text = "Wipe Wallet",
            color = Color(0xfffedcdb),
            drawableResource = R.drawable.icon_delete
        ){}
        HomeScreenActionItem(
            text = "Transfer Out",
            color = Color(0xffc4f0bb),
            drawableResource = R.drawable.icon_upload
        ){}
    }

}

@Composable
fun HomeScreenActionItem(
    text : String,
    @DrawableRes drawableResource : Int,
    color : Color,
    onClick : () -> Unit
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
            HomeScreenActions()
        }
    }
}

@Preview
@Composable
fun HomeScreenActionItemPreview() {

    KryptoTheme {
        Surface {
            HomeScreenActionItem(
                text = "Wipe Wallet",
                color = Color(0xffa1f5b9),
                drawableResource = R.drawable.icon_download
            ){}
        }
    }
}