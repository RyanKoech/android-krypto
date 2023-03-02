package com.ryankoech.krypto.feature_coin_details.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.components.loading.CardLoading
import com.ryankoech.krypto.common.presentation.components.loading.CoinCardLoading
import com.ryankoech.krypto.common.presentation.components.loading.loadingEffect
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme

@Composable
fun CoinDetailsScreenLoading(
   modifier: Modifier = Modifier,
) {

    val brush = loadingEffect()

    LazyColumn(
        modifier = modifier
            .padding(top = 12.dp, start = 12.dp, end = 12.dp)
            .fillMaxSize()
    ){

        item{
            CoinCardLoading(
                brush = brush,
            )
        }

        item{ Spacer(modifier = Modifier.height(32.dp)) }

        item{
            CardLoading(
                modifier = Modifier
                    .height(280.dp),
                brush = brush,
            )
        }

        item{ Spacer(modifier = Modifier.height(24.dp)) }

        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {

                items((1..5).toList()){
                    CardLoading(
                        modifier = Modifier
                            .height(28.dp)
                            .width(36.dp),
                        brush = brush,
                    )
                }

            }
        }

        item{ Spacer(modifier = Modifier.height(24.dp)) }

        item{

            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(216.dp),
                contentPadding = PaddingValues(horizontal = 12.dp),
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalArrangement = Arrangement.SpaceAround,
                userScrollEnabled = false,
            ) {

                items((1..4).toList()) {
                    CardLoading(
                        modifier = Modifier
                            .height(80.dp),
                        brush = brush
                    )
                }

            }

        }



    }


}

@Preview
@Composable
fun CoinDetailsScreenLoadingPreview() {

    KryptoTheme {
        Surface {
            CoinDetailsScreenLoading()
        }
    }

}