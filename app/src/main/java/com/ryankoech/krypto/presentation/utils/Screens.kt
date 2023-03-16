package com.ryankoech.krypto.presentation.utils

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ryankoech.krypto.R

sealed class Screens(
    val route : String,
    @StringRes val labelResId : Int,
    @DrawableRes val iconResId : Int = R.drawable.icon_home,
    @StringRes val titleResId : Int = R.string.app_name,
) {
    object Home : Screens(
        route = "home",
        labelResId = R.string.screen_label_home,
        iconResId = R.drawable.icon_home,
        titleResId = R.string.screen_title_home
    )
    object ChooseAsset : Screens(
        route = "choose-asset",
        labelResId = R.string.screen_label_choose_asset,
        titleResId = R.string.screen_title_choose_asset
    )
    object Transaction : Screens(
        route = "transaction",
        labelResId = R.string.screen_label_transaction,
        titleResId = R.string.screen_title_transaction
    )
    object CoinList : Screens(
        route = "coin-list",
        labelResId = R.string.screen_label_coin_list,
        iconResId = R.drawable.icon_bar_graph,
        titleResId = R.string.screen_title_coin_list
    )
    object CoinDetails : Screens(
        route = "coin-details",
        labelResId = R.string.screen_label_coin_details,
        titleResId = R.string.screen_title_coin_details
    )
    object Settings : Screens(
        route = "settings",
        labelResId = R.string.screen_label_settings,
        iconResId = R.drawable.icon_settings,
        titleResId = R.string.screen_title_settings
    )
    object About : Screens(
        route = "about",
        labelResId = R.string.screen_label_about,
        titleResId = R.string.screen_title_about
    )
}
