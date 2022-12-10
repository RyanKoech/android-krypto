package com.ryankoech.krypto.feature_home.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme


data class CreditCardDetails(
    val balance : Double = 0.0,
    val count : Int = 0,
    val change : Float = 0F,
)

@Composable
fun CreditCard() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(20.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xff7cf057),
                            Color(0xff7cf057),
                            Color(0xffe5ff7b),
                            Color(0xff6bcfda),
                        )
                    )
                ),
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {

                Row {
                    Text(
                        text = "Estimated Balance",
                        style = MaterialTheme.typography.h4
                    )
                    Spacer(modifier = Modifier.weight(1.0f))
                    Card(
                        border = BorderStroke(1.dp,Color.Black),
                        backgroundColor = Color(0xffc4f0bb),
                        elevation = 0.dp,
                        shape = RoundedCornerShape(100)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(start = 6.dp, top = 6.dp, end = 4.dp, bottom = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "USD",
                                style = MaterialTheme.typography.h4,
                                fontSize = 12.sp
                            )
                            Icon(
                                modifier = Modifier
                                    .size(16.dp),
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "",
                            )
                        }
                    }
                }

                Text(
                    text = "$1,723.22",
                    style = MaterialTheme.typography.h1,
                    fontSize = 30.sp
                )

                Spacer(modifier = Modifier.weight(1.0f))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "2 Assets",
                        style = MaterialTheme.typography.h4,
                        fontSize = 12.sp
                    )
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription =""
                    )
                    Text(
                        text = "+2.23%",
                        style = MaterialTheme.typography.h4
                    )
                }
            }
        }

    }
}

@Preview
@Composable
fun CreditCardPreview() {
    KryptoTheme {
        Surface {
            CreditCard()
        }
    }
}