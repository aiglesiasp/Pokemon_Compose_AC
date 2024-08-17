package com.aiglepub.pokemoncompose.ui.screens.pokemonhome

import app.cash.turbine.test
import com.aiglepub.pokemoncompose.Result
import com.aiglepub.pokemoncompose.domain.usecases.FetchPokemonsUseCase
import com.aiglepub.pokemoncompose.helpers.CoroutinesTestRule
import com.aiglepub.pokemoncompose.helpers.generateListPokemons
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class PokemonViewModelTest {
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    private lateinit var fetchPokemonsUseCase: FetchPokemonsUseCase

    private lateinit var vm: PokemonViewModel

    @Before
    fun setUp() {
        vm = PokemonViewModel(fetchPokemonsUseCase)
    }

    @Test
    fun `Pokemons are not requested if UI is not ready`() = runTest {
        vm.state.first()
        runCurrent()

        verify(fetchPokemonsUseCase, times(0)).invoke()
    }

    @Test
    fun `Pokemons are request if UI is ready`() = runTest {
        val pokemons = generateListPokemons(1,2,3)
        whenever(fetchPokemonsUseCase()).thenReturn(flowOf(pokemons))

        vm.onUiReady()

        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(pokemons), awaitItem())
        }
    }

    @Test
    fun `Error is propagated when request fails`() = runTest {
        val error = RuntimeException("Boom!")
        whenever(fetchPokemonsUseCase()).thenThrow(error)

        vm.onUiReady()

        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            val exceptionMessage = (awaitItem() as Result.Error).exception.message
            assertEquals("Boom!", exceptionMessage)
        }
    }
}