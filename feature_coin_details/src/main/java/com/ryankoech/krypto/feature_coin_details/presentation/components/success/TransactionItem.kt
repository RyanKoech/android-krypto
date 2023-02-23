package com.ryankoech.krypto.feature_coin_details.presentation.components.success

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.theme.Green500
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.presentation.theme.Red400
import com.ryankoech.krypto.common.presentation.util.getFormattedBalance
import com.ryankoech.krypto.feature_coin_details.R
import com.ryankoech.krypto.feature_transaction.core.ktx.bottomBorder
import com.ryankoech.krypto.feature_transaction.data.dto.transaction_dto.TransactionDto
import com.ryankoech.krypto.feature_transaction.data.dto.transaction_dto.TransactionType
import com.ryankoech.krypto.feature_transaction.data.repository.bitCoinTransaction
import java.text.SimpleDateFormat
import java.util.*

// @RequiresApi(Build.VERSION_CODES.N)
fun convertLongToTime(time: Long): String {
    val date = Date(time)
    val format = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        SimpleDateFormat("MMMM dd YYYY hh:mm:ss aa")
    } else {
        SimpleDateFormat("MM-dd-yyyy")
    }
    return format.format(date)
}

@Composable
fun TransactionItem(
    modifier: Modifier = Modifier,
    transaction: TransactionDto,
) {

    val context = LocalContext.current
    val isBuyingTransaction = transaction.transactionType == TransactionType.BUY

    Box(
        modifier = modifier
            .bottomBorder(
                strokeWidth = 1.dp,
                color = Color(0xfff2f2f2),
            ),
    ) {
        Row(
            modifier = Modifier
                .padding(
                    vertical = 8.dp,
                    horizontal = 4.dp,
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "${
                            getFormattedBalance(
                                context,
                                transaction.currentPrice * transaction.amount
                            )
                        } bought",
                        style = MaterialTheme.typography.h3,
                    )
                    Text(
                        modifier = Modifier
                            .alpha(0.6f),
                        text = "${if (isBuyingTransaction) "+" else "-"}${transaction.amount} BTC",
                        style = MaterialTheme.typography.body1,
                    )

                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        modifier = Modifier
                            .alpha(0.6f),
                        text = convertLongToTime(transaction.date),
                        style = MaterialTheme.typography.caption,
                    )
                    Icon(
                        modifier = Modifier
                            .scale(
                                scaleX = 1f,
                                scaleY = if (isBuyingTransaction) -1f else 1f
                            ),
                        painter = painterResource(R.drawable.icon_graphline_up),
                        contentDescription = null,
                        tint = if (isBuyingTransaction) Green500 else Red400,
                    )

                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Icon(
                modifier = Modifier
                    .size(36.dp),
                painter = painterResource(R.drawable.icon_delete),
                contentDescription = null,
            )

        }
    }

}

@Preview
@Composable
fun TransactionItemPreview() {

    KryptoTheme {
        Surface {
            TransactionItem(
                transaction = bitCoinTransaction.first()
            )
        }
    }

}