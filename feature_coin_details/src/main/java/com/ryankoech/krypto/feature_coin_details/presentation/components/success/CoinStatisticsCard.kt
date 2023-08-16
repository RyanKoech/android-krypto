package com.ryankoech.krypto.feature_coin_details.presentation.components.success

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.presentation.util.KryptoPreview
import com.ryankoech.krypto.feature_coin_details.R

@Composable
fun CoinStatisticsCard(
    @DrawableRes resId : Int,
    title : String,
    body : String,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier,
        backgroundColor = Color(0xfff2f2f2),
        shape = RoundedCornerShape(50F),
        elevation = 0.dp
    ) {

        Row(
            modifier = Modifier
                .padding(
                    horizontal = 20.dp,
                    vertical = 24.dp,
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                modifier = Modifier
                    .size(32.dp),
                painter = painterResource(resId),
                contentDescription = title
            )

            Column(
                modifier = Modifier
                    .weight(1.0f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h4,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(
                    modifier = Modifier
                        .height(4.dp)
                )

                Text(
                    text = body,
                    style = MaterialTheme.typography.caption,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }

    }

}

@KryptoPreview
@Composable
fun CoinStatisticsCardPreview() {

    KryptoTheme {
        Surface {
            CoinStatisticsCard(
                R.drawable.icon_line_graph,
                "Total Volume",
                "1.2111M"
            )
        }
    }

}