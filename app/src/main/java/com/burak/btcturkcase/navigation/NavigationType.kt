package com.burak.btcturkcase.navigation

sealed class NavigationType {
    data class Navigate(val route: Route) : NavigationType()
    data class ClearBackStackNavigate(val route: Route, val popUpRoute: Route? = null) : NavigationType()
    data class PopBackNavigate(val route: Route) : NavigationType()
    data class ToRouteNotInclusivePopUp(val route: Route) : NavigationType()
    data object PopBack : NavigationType()
}