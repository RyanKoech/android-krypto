package com.ryankoech.krypto.presentation.utils

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ryankoech.krypto.R

sealed class Screens(
    val route : String,
    @StringRes val labelResId : Int,
    @DrawableRes val iconResId : Int = R.drawable.icon_home
) {
    object Home : Screens("home", R.string.screen_label_home, R.drawable.icon_home)
    object ChooseAsset : Screens("choose-asset", R.string.screen_label_choose_asset)
    object Transaction : Screens("transaction", R.string.screen_label_transaction)
    object CoinList : Screens("coin-list", R.string.screen_label_coin_list, R.drawable.icon_bar_graph)
    object CoinDetails : Screens("coin-details", R.string.screen_label_coin_details)
    object Settings : Screens("settings", R.string.screen_label_settings, R.drawable.icon_settings)
}
