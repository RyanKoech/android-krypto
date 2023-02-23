package com.ryankoech.krypto.feature_coin_details.presentation.components.success

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme

@Composable
fun MarketRangeButton(
    onClick : () -> Unit,
    text : String,
    selected : Boolean,
    modifier: Modifier = Modifier
) {

    val colorDark = Color(0xff35343a)

    Button(
        modifier = modifier
            .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if(selected) colorDark else Color.White
        ),
        shape = MaterialTheme.shapes.small,
        // shape = RoundedCornerShape(0.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp
        ),
    ){
        Text(
            text = text,
            style = MaterialTheme.typography.button.copy(
                color = if(selected) Color.White else colorDark
            )
        )
    }

}

@Preview
@Composable
fun MarketRangeButtonPreview() {

    KryptoTheme {
        Surface {
            MarketRangeButton(
                onClick = {},
                text = "1h",
                selected = true
            )
        }
    }

}