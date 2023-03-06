package com.ryankoech.krypto.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.feature_coin_details.presentation.CoinDetailsScreen
import com.ryankoech.krypto.feature_coin_list.domain.entity.Coin as CoinListCoin
import com.ryankoech.krypto.feature_coin_list.presentation.CoinListScreen
import com.ryankoech.krypto.feature_home.presentation.HomeScreen
import com.ryankoech.krypto.feature_transaction.data.dto.transaction_dto.TransactionType
import com.ryankoech.krypto.feature_transaction.domain.entity.Coin as TransactionCoin
import com.ryankoech.krypto.feature_transaction.presentation.choose_asset.ChooseAssetScreen
import com.ryankoech.krypto.feature_transaction.presentation.transaction.TransactionScreen
import com.ryankoech.krypto.presentation.utils.Screens
import com.ryankoech.krypto.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = hiltViewModel<MainViewModel>()
            val navController = rememberNavController()

            val items = listOf(
                Screens.Home,
                Screens.CoinList,
                Screens.Settings
            )

            fun navigateToTransactionScreen(coin : TransactionCoin, transactionType: String) {
                viewModel.addTransactionCoin(coin)
                navController.navigate(Screens.Transaction.route + "/$transactionType"){
                    launchSingleTop = true
                }
            }

            fun navigateToChooseAssetScreen(transactionType: String) {
                navController.navigate(Screens.ChooseAsset.route + "/$transactionType"){
                    launchSingleTop = true
                }
            }

            fun navigateToHomeScreen() {
                navController.navigate(Screens.Home.route) {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    // Avoid multiple copies of the same destination when
                    // reselecting the same item
                    launchSingleTop = true
                    // Restore state when reselecting a previously selected item
                    restoreState = true
                }
            }

            fun navigateToCoinDetails(coin : CoinListCoin) {
                viewModel.addCoinListCoin(coin)
                navController.navigate(Screens.CoinDetails.route){
                    launchSingleTop = true
                }
            }


            KryptoTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigation {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            items.forEach { screen ->
                                BottomNavigationItem(
                                    icon = { Icon(painterResource(screen.iconResId), contentDescription = null) },
                                    label = { Text(stringResource(screen.labelResId)) },
                                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            // Pop up to the start destination of the graph to
                                            // avoid building up a large stack of destinations
                                            // on the back stack as users select items
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            // Avoid multiple copies of the same destination when
                                            // reselecting the same item
                                            launchSingleTop = true
                                            // Restore state when reselecting a previously selected item
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }

                ) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = MaterialTheme.colors.background
                    ) {

                        NavHost(navController = navController, startDestination = "home") {

                            composable(Screens.Home.route) {
                                HomeScreen(
                                    onTransferInClick = {
                                        navigateToChooseAssetScreen(TransactionType.BUY.toString())
                                    },
                                    onTransferOutClick = {
                                        navigateToChooseAssetScreen(TransactionType.SELL.toString())
                                    }
                                )
                            }

                            composable(
                                Screens.ChooseAsset.route + "/{transactionType}",
                                    arguments = listOf(
                                        navArgument("transactionType") {
                                            type = NavType.StringType
                                            defaultValue = TransactionType.BUY.toString()
                                        }
                                    )
                            ) { backStackEntry ->
                                ChooseAssetScreen(
                                    toTransactionScreen = ::navigateToTransactionScreen,
                                    toTransactionType = backStackEntry.arguments?.getString("transactionType")!!
                                )
                            }

                            composable(
                                Screens.Transaction.route + "/{transactionType}",
                                arguments = listOf(
                                    navArgument("transactionType") {
                                        type = NavType.StringType
                                        defaultValue = TransactionType.BUY.toString()
                                    }
                                )
                            ) { backStackEntry ->

                                val coin = viewModel.transactionCoin

                                coin?.apply {
                                    TransactionScreen(
                                        coin = coin,
                                        navigateBackToHomeScreen = ::navigateToHomeScreen,
                                        transactionType = backStackEntry.arguments?.getString("transactionType")!!
                                    )
                                    return@composable
                                }

                                // Fall back error screen
                            }

                            composable(Screens.CoinList.route){
                                CoinListScreen(
                                    coinItemOnClick = ::navigateToCoinDetails
                                )
                            }

                            composable(Screens.CoinDetails.route){

                                val coin = viewModel.coinListCoin

                                coin?.apply {
                                    CoinDetailsScreen(
                                        coin = coin,
                                        navigateToBuyTransactionScreen = { transactionCoin ->
                                            navigateToTransactionScreen(transactionCoin, TransactionType.BUY.toString())
                                        },
                                        navigateToSellTransactionScreen = { transactionCoin ->
                                            navigateToTransactionScreen(transactionCoin, TransactionType.SELL.toString())
                                        }
                                    )
                                    return@composable
                                }

                                // Fallback error screen

                            }


                            composable(Screens.Settings.route){
                                Text(
                                    text = "Settings"
                                )
                            }


                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KryptoTheme {
        Greeting("Android")
    }
}