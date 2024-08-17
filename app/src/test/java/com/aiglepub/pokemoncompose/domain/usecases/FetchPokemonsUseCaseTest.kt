package com.aiglepub.pokemoncompose.domain.usecases

import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class FetchPokemonsUseCaseTest {

    @Test
    fun `Invoke should return listPokemons from repository`() {
        //GIVEN
        val pokemonsFlow = flowOf(generateListPokemons(1,2,3))
        val useCase = FetchPokemonsUseCase(mock {
            on { pokemons } doReturn pokemonsFlow
        })

        //WHEN
        val result = useCase()

        //THEN
        assertEquals(pokemonsFlow, result)
    }
}