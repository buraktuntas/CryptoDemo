package com.burak.btcturkcase.util

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.navigation.NavHostController
import com.burak.btcturkcase.navigation.NavigationType

@ExperimentalTextApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
fun UiEvent.handleUiEvent(
    navController: NavHostController,
) {
    when (this) {
        is UiEvent.Navigate<*> -> {
            navController.handleNavigation(navigationType, data)
        }
        else -> {
        }
    }
}


fun NavHostController.handleNavigation(navigation: NavigationType, data: Map<String, Any?>?) {
    when (navigation) {
        is NavigationType.Navigate,
        is NavigationType.PopBackNavigate,
        is NavigationType.ClearBackStackNavigate -> {
            val route = when (navigation) {
                is NavigationType.Navigate -> navigation.route.route
                is NavigationType.PopBackNavigate -> navigation.route.route
                is NavigationType.ClearBackStackNavigate -> navigation.route.route
                else -> null
            }

            if (navigation is NavigationType.PopBackNavigate) {
                popBackStack()
            }

            route?.let {
                navigate(it) {
                    if (navigation is NavigationType.ClearBackStackNavigate) {
                        navigation.popUpRoute?.let { popUpTo(it.route) } ?: popUpTo(0)
                    }
                }
                getBackStackEntry(route).apply {
                    data?.forEach { (key, value) ->
                        savedStateHandle[key] = value
                    }
                }
            }
        }

        is NavigationType.PopBack -> {
            popBackStack()
        }

        is NavigationType.ToRouteNotInclusivePopUp -> {
            popBackStack(route = navigation.route.route, inclusive = false)
        }
    }
}