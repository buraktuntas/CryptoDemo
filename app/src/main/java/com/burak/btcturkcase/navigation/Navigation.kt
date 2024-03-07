package com.burak.btcturkcase.navigation

import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.burak.btcturkcase.presentation.screens.pairChart.PairChartScreen
import com.burak.btcturkcase.presentation.screens.pairChart.PairChartViewModel
import com.burak.btcturkcase.presentation.screens.pairList.PairListScreen
import com.burak.btcturkcase.presentation.screens.pairList.PairListViewModel
import com.burak.btcturkcase.util.handleUiEvent

@ExperimentalTextApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun Navigation(
    navController: NavHostController,
) {

    NavHost(
        navController = navController,
        startDestination = Route.PairListScreen.route
    ) {

        composable(route = Route.PairListScreen.route) {
            val viewModel = hiltViewModel<PairListViewModel>()
            val uiState by viewModel.uiState.collectAsState()

            PairListScreen(
                uiState = uiState,
                onEvent = viewModel::onEvent,
                uiEvent = viewModel.uiEvent,
                onUiEvent = {
                    it.handleUiEvent(navController)
                },
            )
        }

        composable(route = Route.PairChartScreen.route) { currentStackEntry ->
            val viewModel = hiltViewModel<PairChartViewModel>()
            val uiState by viewModel.uiState.collectAsState()
            val pair = currentStackEntry.savedStateHandle.get<String>("pair")

            LaunchedEffect(key1 = true) {
                pair?.let { viewModel.updatePairAndFetchChartData(it) }
            }
            PairChartScreen(
                uiState = uiState,
                onEvent = viewModel::onEvent,
                uiEvent = viewModel.uiEvent,
                onUiEvent = {
                    it.handleUiEvent(navController)
                },
            )
        }
    }
}