package com.burak.btcturkcase.presentation.screens.pairChart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.burak.btcturkcase.presentation.theme.Blue
import com.burak.btcturkcase.presentation.theme.Blue20
import com.burak.btcturkcase.presentation.theme.BlueDark
import com.burak.btcturkcase.presentation.theme.BlueLight
import com.burak.btcturkcase.presentation.theme.White
import com.burak.btcturkcase.util.UiEvent
import com.burak.btcturkcase.util.rememberMarker
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.rememberAxisLabelComponent
import com.patrykandpatrick.vico.compose.axis.rememberAxisLineComponent
import com.patrykandpatrick.vico.compose.axis.vertical.rememberEndAxis
import com.patrykandpatrick.vico.compose.chart.CartesianChartHost
import com.patrykandpatrick.vico.compose.chart.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.chart.layer.rememberLineSpec
import com.patrykandpatrick.vico.compose.chart.layout.fullWidth
import com.patrykandpatrick.vico.compose.chart.rememberCartesianChart
import com.patrykandpatrick.vico.compose.chart.zoom.rememberVicoZoomState
import com.patrykandpatrick.vico.compose.component.shape.shader.color
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.chart.layout.HorizontalLayout
import com.patrykandpatrick.vico.core.component.shape.shader.DynamicShaders
import com.patrykandpatrick.vico.core.model.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.model.lineSeries
import com.patrykandpatrick.vico.core.zoom.Zoom
import kotlinx.coroutines.flow.Flow

@Composable
fun PairChartScreen(
    uiState: PairChartUiState,
    uiEvent: Flow<UiEvent>,
    onEvent: (PairChartUiEvent) -> Unit,
    onUiEvent: (UiEvent) -> Unit,
) {
    val modelProducer = remember { CartesianChartModelProducer.build() }
    val marker = rememberMarker()

    LaunchedEffect(key1 = true) {
        uiEvent.collect { event ->
            onUiEvent(event)
        }
    }

    LaunchedEffect(uiState.chartData) {
        if (uiState.chartData.isNotEmpty()) {
            val timeValues = uiState.chartData.map { it.first }

            val closeValues = uiState.chartData.map { it.second }
            modelProducer.tryRunTransaction { lineSeries { series(x = timeValues, y = closeValues) } }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BlueDark)
            .padding(16.dp)
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    tint = White,
                    contentDescription = "Back Icon",
                    modifier = Modifier
                        .clickable { onEvent(PairChartUiEvent.OnBackClicked) }
                )
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 12.dp),
                    text = "${uiState.pair} Chart",
                    color = White,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(82.dp))

            CartesianChartHost(
                chart =
                rememberCartesianChart(
                    rememberLineCartesianLayer(
                        listOf(rememberLineSpec(DynamicShaders.color(BlueLight)))
                    ),
                    endAxis = rememberEndAxis(
                        guideline = rememberAxisLineComponent(
                            strokeColor = Blue,
                            color = Blue,
                        ),
                        label = rememberAxisLabelComponent(
                            color = White
                        ),
                        tickLength = 0.dp,
                        itemPlacer = AxisItemPlacer.Vertical.count(count = { 4 }),

                        ),
                    bottomAxis = rememberBottomAxis(
                        label = null,
                        guideline = null,
                        tickLength = 0.dp,
                        itemPlacer = AxisItemPlacer.Horizontal.default(spacing = 2),
                    ),
                    persistentMarkers = mapOf(7f to marker),
                ),
                modelProducer = modelProducer,
                modifier = Modifier
                    .background(Blue20)
                    .height(140.dp),
                marker = marker,
                horizontalLayout = HorizontalLayout.fullWidth(),
                zoomState = rememberVicoZoomState(initialZoom = Zoom.Content, zoomEnabled = true),
            )
        }
        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}