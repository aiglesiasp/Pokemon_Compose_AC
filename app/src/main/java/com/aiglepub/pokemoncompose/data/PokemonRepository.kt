package com.aiglepub.pokemoncompose.data

import com.aiglepub.pokemoncompose.data.datasource.PokemonRemoteDataSource

class PokemonRepository(private val pokemonRemoteDataSource: PokemonRemoteDataSource) {

    suspend fun fetchAllPokemons(): List<Pokemon> = pokemonRemoteDataSource.fetchAllPokemons()

    suspend fun fetchPokemonByName(name: String): Pokemon = pokemonRemoteDataSource.fetchPokemonByName(name)
}



