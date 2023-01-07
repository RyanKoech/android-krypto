package com.ryankoech.krypto.common.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.widget.Placeholder
import com.ryankoech.krypto.common.R
import com.ryankoech.krypto.common.presentation.theme.Green200
import com.ryankoech.krypto.common.presentation.theme.Green500
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme

@Composable
fun SearchBar(
    value : String,
    onValueChange : (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .border(width = 1.dp, shape = MaterialTheme.shapes.small, color = Color.Black),
        shape = MaterialTheme.shapes.small,
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.icon_search),
                contentDescription = "Search icon"
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.caption,
                color = Color.Black
            )
        },
        textStyle = MaterialTheme.typography.caption,
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Unspecified,
            unfocusedIndicatorColor = Color.Unspecified,
            cursorColor = Green200,
            backgroundColor = Color.White
        ),
    )


}

@Preview
@Composable
fun SearchBarPreview() {
    KryptoTheme {
        Surface(
            modifier = Modifier.fillMaxWidth()
        ) {


            SearchBar(
                value = "",
                onValueChange = {},
                placeholder = "Discover asset, coin or token"
            )
        }
    }
}