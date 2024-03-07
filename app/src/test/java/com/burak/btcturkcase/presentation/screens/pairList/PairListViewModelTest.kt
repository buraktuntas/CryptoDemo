package com.burak.btcturkcase.presentation.screens.pairList

import com.burak.btcturkcase.common.ResultState
import com.burak.btcturkcase.data.local.PairDataRepository
import com.burak.btcturkcase.data.local.entity.PairInfo
import com.burak.btcturkcase.domain.use_case.GetPairListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PairListViewModelTest {

    private lateinit var viewModel: PairListViewModel
    private lateinit var getPairListUseCase: GetPairListUseCase
    private lateinit var pairDataRepository: PairDataRepository
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getPairListUseCase = mockk()
        pairDataRepository = mockk()
        viewModel = PairListViewModel(getPairListUseCase, pairDataRepository)
    }

    @Test
    fun `test PairInfo creation`() {
        val pairInfo = createPairInfo()
        assertEquals("BTCUSDT", pairInfo.pair)
        assertEquals("btcusdt", pairInfo.pairNormalized)
        assertEquals(100.0, pairInfo.volume)
        assertEquals(0.5, pairInfo.dailyPercent)
        assertEquals(50000.0, pairInfo.last)
        assertEquals("BTC", pairInfo.numeratorSymbol)
        assertEquals(true, pairInfo.isFavorite)
    }

    @Test
    fun `test onEvent with PairListUiEvent OnPairItemClicked`() = testDispatcher.runBlockingTest {
        val pairInfo = createPairInfo()
        viewModel.onEvent(PairListUiEvent.OnPairItemClicked(pairInfo))
    }

    @Test
    fun `test onEvent with PairListUiEvent OnFavoriteClicked`() = testDispatcher.runBlockingTest {
        val pairInfo = createPairInfo()
        viewModel.onEvent(PairListUiEvent.OnFavoriteClicked(pairInfo))
    }

    @Test
    fun `test callGetPairList`() = testDispatcher.runBlockingTest {
        val symbol = "USDT"
        val pairInfoList = listOf(createPairInfo())
        coEvery { getPairListUseCase.invoke(any(), any()) } coAnswers {
            val onResult = secondArg<(ResultState<List<PairInfo>>) -> Unit>()
            onResult(ResultState.Success(pairInfoList))
        }

        viewModel.callGetPairList(symbol)

    }

    @Test
    fun `test updateFavoriteItem`() = testDispatcher.runBlockingTest {
        val pairInfo = createPairInfo()
        coEvery { pairDataRepository.updatePair(pairInfo) } returns Unit

        viewModel.updateFavoriteItem(pairInfo)

    }

    @Test
    fun `test updatePageLoading`() = testDispatcher.runBlockingTest {
        val isLoading = true

        viewModel.updatePageLoading(isLoading)

    }

    private fun createPairInfo(): PairInfo {
        return PairInfo(
            pair = "BTCUSDT",
            pairNormalized = "btcusdt",
            volume = 100.0,
            dailyPercent = 0.5,
            last = 50000.0,
            numeratorSymbol = "BTC",
            isFavorite = true
        )
    }
}
