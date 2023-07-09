package com.ryankoech.krypto.presentation.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.presentation.utils.Screens

private val BottomNavigationItems = listOf(
    Screens.Home,
    Screens.CoinList,
    Screens.Settings
)

@Composable
fun KryptoBottomNavigation(
    modifier: Modifier = Modifier,
    selected : (Screens) -> Boolean,
    onClick : (Screens) -> Unit
) {
    BottomNavigation(
        modifier = modifier
            .height(72.dp),
        backgroundColor = Color(0xfff2f2f2),
        contentColor = MaterialTheme.colors.onSurface,
    ) {
        BottomNavigationItems.forEach { screen ->
            BottomNavigationItem(
                modifier = Modifier
                    .padding(bottom = 12.dp),
                icon = {
                    Icon(
                        modifier = Modifier
                            .padding(bottom = 4.dp),
                        painter = painterResource(screen.iconResId),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(screen.labelResId)
                    )
                },
                selected = selected(screen),
                onClick = { onClick(screen) }
            )
        }
    }
}

@Preview
@Composable
fun KryptoBottomNavigationPreview() {
    val isSelected : (Screens) -> Boolean = {
        it == Screens.Home
    }
    KryptoTheme {
        Scaffold(
            bottomBar = {
                KryptoBottomNavigation(
                    selected = isSelected,
                    onClick = {}
                )
            }
        ) {
            Surface(
                modifier = Modifier.padding(it)
            ) {

            }
        }
    }
}