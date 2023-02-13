package com.ryankoech.krypto.common.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.theme.Green200
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme


@Composable
fun KryptoButton(
    modifier: Modifier = Modifier,
    text : String,
    color : Color = Green200,
    enabled : Boolean = true,
    onClick : () -> Unit
) {

    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color
        ),
        enabled = enabled,
        shape = MaterialTheme.shapes.medium,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp
        ),
        onClick = onClick,
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 8.dp),
            text = text,
            style = MaterialTheme.typography.button,
        )
    }

}

@Preview
@Composable
fun KryptoButtonPreview(){

    KryptoTheme {
        Surface {
            KryptoButton(text = "Button") {}
        }
    }

}