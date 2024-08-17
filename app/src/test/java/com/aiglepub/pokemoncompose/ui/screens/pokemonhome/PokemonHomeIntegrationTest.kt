package com.aiglepub.pokemoncompose.ui.screens.pokemonhome

import app.cash.turbine.test
import com.aiglepub.pokemoncompose.Result
import com.aiglepub.pokemoncompose.domain.entities.Pokemon
import com.aiglepub.pokemoncompose.helpers.CoroutinesTestRule
import com.aiglepub.pokemoncompose.helpers.buildPokemonViewModelWith
import com.aiglepub.pokemoncompose.helpers.generateListPokemons
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class PokemonHomeIntegrationTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `Data is loaded from server when local data source is empty`() = runTest {
        val remoteData = generateListPokemons(1, 2, 3)
        val vm = buildPokemonViewModelWith(remoteData = remoteData)

        vm.onUiReady()

        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(emptyList<Pokemon>()), awaitItem())
            assertEquals(Result.Success(remoteData), awaitItem())
        }
    }

    @Test
    fun `Data is loaded from local source when available`() = runTest {
        val localData = generateListPokemons(1, 2, 3)
        val vm = buildPokemonViewModelWith(localData = localData)

        vm.onUiReady()

        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(localData), awaitItem())
        }
    }
}