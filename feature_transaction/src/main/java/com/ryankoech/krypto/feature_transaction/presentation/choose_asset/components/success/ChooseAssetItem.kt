package com.ryankoech.krypto.feature_transaction.presentation.choose_asset.components.success

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.components.CoinImage
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.presentation.util.KryptoPreview
import com.ryankoech.krypto.feature_transaction.R
import com.ryankoech.krypto.feature_transaction.core.ktx.bottomBorder
import com.ryankoech.krypto.feature_transaction.domain.entity.Coin

@Composable
fun ChooseAssetItem(
    onClick : (Coin) -> Unit,
    coin: Coin,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .bottomBorder(
                1.dp,
                Color(0xfff6f6f6)
            )
            .clickable {
                onClick(coin)
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {

        CoinImage(
            modifier = Modifier
                .padding(
                    vertical = 8.dp
                ),
            url = coin.image,
            name = coin.name,
            backgroundColor = Color.Transparent
        )

        Text(
            modifier = Modifier
                .padding(end = 2.dp),
            text = coin.name,
            style = MaterialTheme.typography.h3,
        )

        Spacer(
            modifier = Modifier
                .weight(1f)
        )

        Icon(
            painter = painterResource(R.drawable.icon_chevron_right),
            contentDescription = null
        )

    }

}

@KryptoPreview
@Composable
fun PreviewChooseAssetItem() {

    KryptoTheme {
        Surface {
            Column {
                ChooseAssetItem(
                    {},
                    Coin(
                        id = "bitcoin",
                        image = "",
                        name = "Bitcoin",
                        symbol = "BTC",
                        price = 20_000.0
                    )
                )
            }
        }
    }

}
