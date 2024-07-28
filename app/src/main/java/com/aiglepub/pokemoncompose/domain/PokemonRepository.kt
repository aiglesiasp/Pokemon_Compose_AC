package com.aiglepub.pokemoncompose.domain

import com.aiglepub.pokemoncompose.domain.entities.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    val pokemons: Flow<List<Pokemon>>
    fun fetchPokemonByName(name: String): Flow<Pokemon>
    suspend fun updatePokemon(pokemon: Pokemon)
}