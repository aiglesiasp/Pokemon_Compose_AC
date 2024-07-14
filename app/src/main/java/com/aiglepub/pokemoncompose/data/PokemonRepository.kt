package com.aiglepub.pokemoncompose.data

import com.aiglepub.pokemoncompose.data.datasource.local.PokemonLocalDataSource
import com.aiglepub.pokemoncompose.data.datasource.remote.PokemonRemoteDataSource

class PokemonRepository(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    private val pokemonLocalDataSource: PokemonLocalDataSource
) {

    suspend fun fetchAllPokemons(): List<Pokemon>
    {
        if (pokemonLocalDataSource.isEmpty()) {
            val pokemons = pokemonRemoteDataSource.fetchAllPokemons()
            pokemonLocalDataSource.insertPokemons(pokemons)
        }
        return pokemonLocalDataSource.getAllPokemons()
    }

    suspend fun fetchPokemonByName(name: String): Pokemon
    {
        if (pokemonLocalDataSource.getPokemonByName(name) == null) {
            val pokemon = pokemonRemoteDataSource.fetchPokemonByName(name)
            pokemonLocalDataSource.insertPokemons(listOf(pokemon))
        }
        return checkNotNull(pokemonLocalDataSource.getPokemonByName(name))
    }
}



