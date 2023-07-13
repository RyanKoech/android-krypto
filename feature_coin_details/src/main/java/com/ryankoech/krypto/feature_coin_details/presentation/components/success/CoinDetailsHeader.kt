package com.ryankoech.krypto.feature_coin_details.presentation.components.success

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.components.CoinImage
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.presentation.util.*
import com.ryankoech.krypto.feature_coin_list.data.dto.toCoinEntity
import com.ryankoech.krypto.feature_coin_list.data.repository.FAKE_COIN_LIST
import com.ryankoech.krypto.feature_coin_list.domain.entity.Coin
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CoinDetailsHeader(
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
            ),
    ) {
        CoinImage(
            coin.image,
            coin.id,
            backgroundColor = MaterialTheme.colors.surface,
        )

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
                        .weight(1f)
                        .padding(end = 4.dp)
                        .basicMarquee(),
                    text = coin.name,
                    maxLines = 1,
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
                            color = Color.LightGray,
                            shape = RoundedCornerShape(3.dp)
                        ),

                    ) {
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
                    text = coin.symbol.uppercase(Locale.ROOT),
                    style = MaterialTheme.typography.h3,
                )
                Spacer(modifier = Modifier.weight(1.0f))
                Icon(
                    modifier = Modifier
                        .size(32.dp)
                        .rotate(if (coin.change < 0f) 0f else 180f),
                    imageVector = Icons.Filled.ArrowDropDown,
                    tint = getChangeColor(coin.change),
                    contentDescription = null,
                )
                Text(
                    text = getFormattedChange(context, coin.change),
                    style = MaterialTheme.typography.caption,
                    color = getChangeColor(coin.change)
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun CoinDetailsHeaderPreview() {
    KryptoTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column {
                CoinDetailsHeader(
                    FAKE_COIN_LIST.toCoinEntity().first()
                )
                CoinDetailsHeader(
                    FAKE_COIN_LIST.toCoinEntity().last()
                )
            }
        }
    }
}