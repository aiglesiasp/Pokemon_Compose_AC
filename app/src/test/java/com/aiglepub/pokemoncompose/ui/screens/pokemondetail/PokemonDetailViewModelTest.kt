package com.aiglepub.pokemoncompose.ui.screens.pokemondetail

import app.cash.turbine.test
import com.aiglepub.pokemoncompose.Result
import com.aiglepub.pokemoncompose.domain.usecases.FetchPokemonByNameUseCase
import com.aiglepub.pokemoncompose.helpers.CoroutinesTestRule
import com.aiglepub.pokemoncompose.helpers.generateSamplePokemon
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class PokemonDetailViewModelTest {
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    private lateinit var fetchPokemonByNameUseCase: FetchPokemonByNameUseCase

    private lateinit var vm: PokemonDetailViewModel

    private val pokemon = generateSamplePokemon(2)

    @Before
    fun setUp() {
        whenever(fetchPokemonByNameUseCase("2")).thenReturn(flowOf(pokemon))
        vm = PokemonDetailViewModel("2", fetchPokemonByNameUseCase)
    }

    @Test
    fun `UI is updated with the movie on start`() = runTest {
        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(pokemon), awaitItem())
        }
    }
}