package com.ryankoech.krypto.feature_home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.components.CoinImage
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.feature_home.data.dto.owned_coin.OwnedCoinDto
import com.ryankoech.krypto.feature_home.presentation.util.DisplayCurrency
import ke.co.sevenskies.feature_home.R

@Composable
fun OwnedCoinItem(ownedCoinDto: OwnedCoinDto) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            ),
    ) {
        CoinImage(ownedCoinDto.icon, ownedCoinDto.id)

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
                    text = ownedCoinDto.symbol,
                    style = MaterialTheme.typography.h3,
                )
                Text(
                    text = getFormattedChange(ownedCoinDto.change.toFloat()),
                    style = MaterialTheme.typography.body1,
                    color = getChangeColor(ownedCoinDto.change.toFloat())
                )
                Spacer(modifier = Modifier.weight(1.0f))
                Text(
                    text = getFormattedBalance(
                        balance = (ownedCoinDto.amount * ownedCoinDto.value).toDouble(),
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
                        balance = ownedCoinDto.value.toDouble(),
                        displayCurrency = DisplayCurrency.USD
                    ),
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = stringResource(R.string.coin_amount, ownedCoinDto.amount.toString(), ownedCoinDto.symbol),
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
                        OwnedCoinDto(
                            id = "bitcoin",
                            symbol = "BTC",
                            amount = 2.10545f,
                            value = 17300.105f,
                            change = -2.45,
                            icon = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png"
                        )
                    )
                }
            }
        }
}