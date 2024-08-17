package com.aiglepub.pokemoncompose.domain.usecases

import com.aiglepub.pokemoncompose.helpers.generateSamplePokemon
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class FetchPokemonByNameUseCaseTest {
    @Test
    fun `Invoke should return pokemon by Name from repository`() {
        //GIVEN
        val pokemonFlow = flowOf(generateSamplePokemon(1))
        val useCase = FetchPokemonByNameUseCase(mock {
            on { fetchPokemonByName("Name 1") } doReturn pokemonFlow
        })

        //WHEN
        val result = useCase("Name 1")

        //THEN
        assertEquals(pokemonFlow, result)
    }
}