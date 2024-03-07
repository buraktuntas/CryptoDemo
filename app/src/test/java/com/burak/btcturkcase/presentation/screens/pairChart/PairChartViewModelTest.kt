package com.burak.btcturkcase.presentation.screens.pairChart


import com.burak.btcturkcase.common.ResultState
import com.burak.btcturkcase.domain.model.ChartData
import com.burak.btcturkcase.domain.use_case.GetPairChartUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PairChartViewModelTest {

    private lateinit var viewModel: PairChartViewModel
    private lateinit var getPairChartUseCase: GetPairChartUseCase
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getPairChartUseCase = mockk()
        viewModel = PairChartViewModel(getPairChartUseCase)
    }

    @Test
    fun `test fetchChartData`() = testDispatcher.runBlockingTest {
        val chartDataList = listOf(
            ChartData(1615219200, 50000F),
            ChartData(1615305600, 51000F),
        )
        coEvery { getPairChartUseCase.invoke(any(), any(), any(), any(), any()) } coAnswers {
            val onResult = secondArg<(ResultState<List<ChartData>>) -> Unit>()
            onResult(ResultState.Success(chartDataList))
        }

        viewModel.fetchChartData()
    }

    @Test
    fun `test updatePairAndFetchChartData`() = testDispatcher.runBlockingTest {
        val symbol = "BTCUSDT"
        val chartDataList = listOf(
            ChartData(1615219200, 50000F),
            ChartData(1615305600, 51000F),
        )
        coEvery { getPairChartUseCase.invoke(any(), any(), any(), any(), any()) } coAnswers {
            val onResult = secondArg<(ResultState<List<ChartData>>) -> Unit>()
            onResult(ResultState.Success(chartDataList))
        }

        viewModel.updatePairAndFetchChartData(symbol)
    }

    @Test
    fun `test calculateTimeRange`() {
        val (now, from, to) = viewModel.calculateTimeRange()
    }
}
