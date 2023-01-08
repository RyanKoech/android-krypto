package com.ryankoech.krypto.feature_coin_list.presentation.components.success

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.feature_coin_list.R
import com.ryankoech.krypto.feature_coin_list.domain.entity.Order
import com.ryankoech.krypto.feature_coin_list.presentation.theme.black100
import com.ryankoech.krypto.feature_coin_list.presentation.theme.limeGreenYellow400

@Composable
fun SearchTag(
    onClick : () -> Unit,
    isSelected : Boolean,
    order : Order,
    text : String,
    modifier: Modifier = Modifier,
) {

    val spacingValue = 4.dp

    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if(isSelected) limeGreenYellow400 else black100,
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp
        ),
        onClick = onClick
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

@Preview
@Composable
fun SearchTagPreview() {

    KryptoTheme {
        Surface {
            SearchTag(
                onClick = {},
                isSelected = true,
                order = Order.ASC,
                text = "Market Cap"
            )
        }
    }

}