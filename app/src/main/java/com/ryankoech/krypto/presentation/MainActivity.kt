package com.ryankoech.krypto.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import com.ryankoech.krypto.R
import com.ryankoech.krypto.common.presentation.theme.KryptoTheme
import com.ryankoech.krypto.feature_coin_details.presentation.CoinDetailsScreen
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

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            var topBarTitle by remember {
                mutableStateOf("")
            }

            val bottomNavigationItems = listOf(
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
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }

            fun navigateToCoinDetails(coinId : String) {
                navController.navigate(Screens.CoinDetails.route + "/$coinId"){
                    launchSingleTop = true
                }
            }


            KryptoTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = topBarTitle,
                                    style = MaterialTheme.typography.h1
                                )
                            },
                            navigationIcon =
                                if (bottomNavigationItems.none { it.route == currentDestination?.route }) {
                                    {
                                        IconButton(onClick = {
                                            navController.popBackStack()
                                        }) {
                                            Icon(
                                                painter = painterResource(R.drawable.icon_arrow_left),
                                                contentDescription = "Go back"
                                            )
                                        }
                                    }
                                } else null,
                        )
                    },
                    bottomBar = {
                        BottomNavigation {
                            bottomNavigationItems.forEach { screen ->
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
                                topBarTitle = stringResource(Screens.Home.titleResId)
                                HomeScreen(
                                    onTransferInClick = {
                                        navigateToChooseAssetScreen(TransactionType.BUY.toString())
                                    },
                                    onTransferOutClick = {
                                        navigateToChooseAssetScreen(TransactionType.SELL.toString())
                                    },
                                    navigateToCoinDetails = ::navigateToCoinDetails
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
                                topBarTitle = stringResource(Screens.ChooseAsset.titleResId)
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
                                topBarTitle = stringResource(Screens.Transaction.titleResId)

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
                                topBarTitle = stringResource(Screens.CoinList.titleResId)
                                CoinListScreen(
                                    coinItemOnClick = ::navigateToCoinDetails
                                )
                            }

                            composable(
                                Screens.CoinDetails.route + "/{coinId}",
                                arguments = listOf(
                                    navArgument("coinId") {
                                        type = NavType.StringType
                                        defaultValue = "bitcoin"
                                    }
                                )
                            ){ backStackEntry ->
                                topBarTitle = stringResource(Screens.CoinDetails.titleResId)

                                CoinDetailsScreen(
                                    coinId = backStackEntry.arguments?.getString("coinId")!!,
                                    navigateToBuyTransactionScreen = { transactionCoin ->
                                        navigateToTransactionScreen(transactionCoin, TransactionType.BUY.toString())
                                    },
                                    navigateToSellTransactionScreen = { transactionCoin ->
                                        navigateToTransactionScreen(transactionCoin, TransactionType.SELL.toString())
                                    }
                                )

                            }


                            composable(Screens.Settings.route){
                                topBarTitle = stringResource(Screens.Settings.titleResId)
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