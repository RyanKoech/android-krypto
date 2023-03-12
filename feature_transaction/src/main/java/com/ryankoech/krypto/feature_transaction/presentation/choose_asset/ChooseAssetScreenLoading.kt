package com.ryankoech.krypto.feature_transaction.presentation.choose_asset

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.krypto.common.presentation.components.loading.loadingEffect
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.feature_transaction.presentation.choose_asset.components.loading.ChooseAssetItemLoading

@Composable
fun ChooseAssetScreenLoading(
    modifier: Modifier = Modifier
) {

    val brush = loadingEffect()

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(12.dp),
    ) {
        items(count = 15) {
            Spacer(Modifier.height(8.dp))
            ChooseAssetItemLoading(brush)
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Preview
@Composable
fun ChooseAssetScreenLoadingPreview() {

     KryptoTheme {
        Surface {
            ChooseAssetScreenLoading()
        }
     }

}