package com.ryankoech.krypto.feature_home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme

@Composable
fun HomeScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .padding(top = 12.dp, start = 12.dp, end = 12.dp)
                .fillMaxSize()
        ) {
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    KryptoTheme {
        HomeScreen()
    }
}