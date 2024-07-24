package com.aiglepub.pokemoncompose.data.remote

import com.aiglepub.pokemoncompose.domain.entities.Pokemon

interface PokemonRemoteDataSource {
    suspend fun fetchAllPokemons(): List<Pokemon>
    suspend fun fetchPokemonByName(name: String): Pokemon
}

