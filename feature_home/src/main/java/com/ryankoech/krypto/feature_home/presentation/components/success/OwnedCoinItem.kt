package com.ryankoech.krypto.feature_home.presentation.components.success

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.components.CoinImage
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.OwnedCoinDto
import com.ryankoech.krypto.common.presentation.util.DisplayCurrency
import com.ryankoech.krypto.common.presentation.util.getChangeColor
import com.ryankoech.krypto.common.presentation.util.getFormattedBalance
import com.ryankoech.krypto.common.presentation.util.getFormattedChange
import com.ryankoech.krypto.feature_home.R

@Composable
fun OwnedCoinItem(
    onClick : () -> Unit,
    ownedCoinDto: OwnedCoinDto,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable {
                onClick()
            },
    ) {
        CoinImage(ownedCoinDto.icon, ownedCoinDto.id)

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxHeight()
                .weight(1.0f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(end = 2.dp),
                    text = ownedCoinDto.symbol.uppercase(),
                    style = MaterialTheme.typography.h3,
                )
                Text(
                    text = getFormattedChange(context, ownedCoinDto.change),
                    style = MaterialTheme.typography.body1,
                    color = getChangeColor(ownedCoinDto.change)
                )
                Spacer(modifier = Modifier.weight(1.0f))
                Text(
                    text = getFormattedBalance(
                        context = context,
                        balance = (ownedCoinDto.amount * ownedCoinDto.value),
                        displayCurrency = DisplayCurrency.USD
                    ),
                    style = MaterialTheme.typography.h4
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(0.6f),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = getFormattedBalance(
                        context = context,
                        balance = ownedCoinDto.value,
                        displayCurrency = DisplayCurrency.USD
                    ),
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = stringResource(R.string.coin_amount, ownedCoinDto.amount.toString(), ownedCoinDto.symbol).uppercase(),
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun OwnedCoinItemPreview() {
        KryptoTheme {
            Surface(
                 modifier = Modifier
                    .fillMaxSize()
            ) {
                Column {
                    OwnedCoinItem(
                        {},
                        OwnedCoinDto(
                            id = "bitcoin",
                            symbol = "BTC",
                            amount = 2.10545,
                            value = 17300.105,
                            change = -2.45f,
                            icon = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png"
                        )
                    )
                }
            }
        }
}