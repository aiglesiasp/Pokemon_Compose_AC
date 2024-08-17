package com.aiglepub.pokemoncompose.data

import com.aiglepub.pokemoncompose.data.local.PokemonLocalDataSource
import com.aiglepub.pokemoncompose.data.remote.PokemonRemoteDataSource
import com.aiglepub.pokemoncompose.domain.PokemonRepository
import com.aiglepub.pokemoncompose.domain.entities.Pokemon
import com.aiglepub.pokemoncompose.helpers.generateListPokemons
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class PokemonRepositoryImplTest {

    @Mock
    lateinit var remoteDataSource: PokemonRemoteDataSource

    @Mock
    lateinit var  localDataSource: PokemonLocalDataSource

    private lateinit var repository: PokemonRepository

    @Before
    fun setUp() {
        repository = PokemonRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun `Pokemons are taken from local data source if available`(): Unit = runBlocking {
        //GIVEN
        val localPokemons = generateListPokemons(1,2,3)
        whenever(localDataSource.pokemons).thenReturn(flowOf(localPokemons))
        //WHEN
        val result = repository.pokemons
        //THEN
        assertEquals(localPokemons, result.first())
    }

    @Test
    fun `Pokemons are saved to local data source when it's empty`(): Unit = runBlocking {
        //GIVEN
        val localPokemons = emptyList<Pokemon>()
        val remotePokemons = generateListPokemons(1,2,3)
        whenever(localDataSource.pokemons).thenReturn(flowOf(localPokemons))
        whenever(remoteDataSource.fetchAllPokemons()).thenReturn(remotePokemons)
        //WHEN
        repository.pokemons.first()
        //THEN
        verify(localDataSource).insertPokemons(remotePokemons)
    }
}