package com.ryankoech.krypto.feature_coin_list.presentation.components.success

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.common.presentation.util.KryptoPreview
import com.ryankoech.krypto.feature_coin_list.R
import com.ryankoech.krypto.feature_coin_list.domain.entity.Order
import com.ryankoech.krypto.feature_coin_list.domain.entity.SortCoinBy
import com.ryankoech.krypto.feature_coin_list.domain.entity.SortInfo
import com.ryankoech.krypto.feature_coin_list.presentation.theme.black100
import com.ryankoech.krypto.feature_coin_list.presentation.theme.limeGreenYellow400
import com.ryankoech.krypto.feature_coin_list.presentation.viewmodel.CoinListScreenViewModel.Companion.DEFAULT_SORT_INFO

@Composable
fun SearchTag(
    editSortingInfo : (sortCoinBy : SortCoinBy) -> Unit,
    sortCoinBy: SortCoinBy,
    sortInfoState : SortInfo,
    text : String,
    modifier: Modifier = Modifier,
) {

    val spacingValue = 4.dp
    val isSelected = sortCoinBy == sortInfoState.sortBy
    val order = sortInfoState.order

    Button(
        modifier = modifier.animateContentSize(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if(isSelected) limeGreenYellow400 else black100,
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp
        ),
        onClick = {
            editSortingInfo(sortCoinBy)
        }
    ) {

        Spacer(modifier = Modifier.width(spacingValue/2))

        Text(
            modifier = Modifier
                .padding(vertical = spacingValue),
            text = text,
            style = MaterialTheme.typography.caption,
        )

        Spacer(modifier = Modifier.width(spacingValue/2))

        if (isSelected) {

            Icon(
                modifier = Modifier
                    .size(14.dp)
                    .rotate(if (order == Order.DESC) 180F else 0F),
                painter = painterResource(R.drawable.icon_arrow_up),
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(spacingValue/2))
        }
    }

}

@KryptoPreview
@Composable
fun SearchTagPreview() {

    KryptoTheme {
        Surface {
            SearchTag(
                editSortingInfo = {},
                sortCoinBy = SortCoinBy.MARKET_CAP,
                sortInfoState = DEFAULT_SORT_INFO,
                text = "Market Cap"
            )
        }
    }

}