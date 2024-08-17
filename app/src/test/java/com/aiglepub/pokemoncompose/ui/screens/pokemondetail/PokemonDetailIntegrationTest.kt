package com.aiglepub.pokemoncompose.ui.screens.pokemondetail

import app.cash.turbine.test
import com.aiglepub.pokemoncompose.Result
import com.aiglepub.pokemoncompose.domain.usecases.FetchPokemonByNameUseCase
import com.aiglepub.pokemoncompose.helpers.CoroutinesTestRule
import com.aiglepub.pokemoncompose.helpers.buildPokemonRepositoryWith
import com.aiglepub.pokemoncompose.helpers.generateListPokemons
import com.aiglepub.pokemoncompose.helpers.generateSamplePokemon
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PokemonDetailIntegrationTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var vm: PokemonDetailViewModel

    @Before
    fun setUp() {
        val pokemonRepository = buildPokemonRepositoryWith(localData = generateListPokemons(1,2,3))
        val useCaseFind = FetchPokemonByNameUseCase(pokemonRepository)

        vm = PokemonDetailViewModel("Name 2", useCaseFind)
    }

    @Test
    fun `UI is updated with the movie on start`() = runTest {
        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(generateSamplePokemon(2)), awaitItem())
        }
    }
}