package com.aiglepub.pokemoncompose.data.datasource.local

import com.aiglepub.pokemoncompose.domain.entities.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonLocalDataSource {
    val pokemons: Flow<List<Pokemon>>
    fun getPokemonByName(name: String): Flow<Pokemon?>
    suspend fun isEmpty(): Boolean
    suspend fun insertPokemons(pokemons: List<Pokemon>)
}



