package com.burak.btcturkcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.navigation.compose.rememberNavController
import com.burak.btcturkcase.navigation.Navigation
import com.burak.btcturkcase.presentation.theme.Blue
import com.burak.btcturkcase.presentation.theme.BtcTurkCaseTheme
import dagger.hilt.android.AndroidEntryPoint


@OptIn(ExperimentalTextApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Blue.toArgb()
        setContent {
            BtcTurkCaseTheme {
                val navController = rememberNavController()
                Navigation(
                    navController = navController,
                )
            }
        }
    }
}