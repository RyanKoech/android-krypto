package com.ryankoech.krypto.feature_coin_list.presentation.components.success

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
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.components.CoinImage
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.presentation.util.*
import com.ryankoech.krypto.feature_coin_list.R
import com.ryankoech.krypto.feature_coin_list.data.dto.toCoinEntity
import com.ryankoech.krypto.feature_coin_list.data.repository.FAKE_COIN_LIST
import com.ryankoech.krypto.feature_coin_list.domain.entity.Coin
import com.ryankoech.krypto.feature_coin_list.presentation.theme.black100
import com.ryankoech.krypto.feature_coin_list.presentation.theme.black200

@Composable
fun CoinItem(
    onClick : (Coin) -> Unit,
    coin: Coin,
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
                onClick(coin)
            },
    ) {
        CoinImage(coin.image, coin.id)

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxHeight()
                .weight(1.0f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .padding(end = 2.dp),
                    text = coin.name,
                    style = MaterialTheme.typography.h3,
                )
                Text(
                    text = getFormattedBalance(
                        context = context,
                        balance = coin.price,
                        displayCurrency = DisplayCurrency.USD
                    ),
                    style = MaterialTheme.typography.h4
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .padding(
                            end = 4.dp
                        )
                        .background(
                            color = black200,
                            shape = RoundedCornerShape(3.dp)
                        ),

                ){
                   Text(
                       modifier = Modifier
                           .padding(
                               vertical = 0.dp,
                               horizontal = 3.dp
                           ),
                       text = coin.marketCapRank.toString(),
                       style = MaterialTheme.typography.h4,
                       color = Color.White
                   )
                }
                Text(
                    modifier = Modifier
                        .padding(end = 2.dp)
                        .alpha(0.6f),
                    text = coin.symbol.uppercase(),
                    style = MaterialTheme.typography.h3,
                )
                Text(
                    text = getFormattedChange(context, coin.change),
                    style = MaterialTheme.typography.body1,
                    color = getChangeColor(coin.change)
                )
                Spacer(modifier = Modifier.weight(1.0f))
                Text(
                    modifier = Modifier
                        .alpha(0.6f),
                    text = getFormattedMarketCap(context, coin.marketCap),
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun CoinItemPreview() {
    KryptoTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column {
                CoinItem(
                    {},
                    FAKE_COIN_LIST.toCoinEntity().first()
                )
            }
        }
    }
}