package com.aiglepub.pokemoncompose.data

import com.aiglepub.pokemoncompose.data.datasource.local.PokemonLocalDataSource
import com.aiglepub.pokemoncompose.data.datasource.remote.PokemonRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

class PokemonRepository(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    private val pokemonLocalDataSource: PokemonLocalDataSource
) {

    val pokemons: Flow<List<Pokemon>> = pokemonLocalDataSource.pokemons.transform { localPokemons ->
        val pokemons = localPokemons.takeIf { it.isNotEmpty() }
            ?: pokemonRemoteDataSource.fetchAllPokemons().also {
                pokemonLocalDataSource.insertPokemons(it)
            }
        emit(pokemons)
    }
    /*
    suspend fun fetchAllPokemons(): List<Pokemon>
    {
        if (pokemonLocalDataSource.isEmpty()) {
            val pokemons = pokemonRemoteDataSource.fetchAllPokemons()
            pokemonLocalDataSource.insertPokemons(pokemons)
        }
        return pokemonLocalDataSource.getAllPokemons()
    }
     */




    fun fetchPokemonByName(name: String): Flow<Pokemon?> = pokemonLocalDataSource.getPokemonByName(name).transform { localPokemon ->
        val pokemon = localPokemon.takeIf { it != null }
            ?: pokemonRemoteDataSource.fetchPokemonByName(name).also {
            pokemonLocalDataSource.insertPokemons(listOf(it))
        }
        emit(pokemon)
    }

    /*
    suspend fun fetchPokemonByName(name: String): Pokemon
    {
        if (pokemonLocalDataSource.getPokemonByName(name) == null) {
            val pokemon = pokemonRemoteDataSource.fetchPokemonByName(name)
            pokemonLocalDataSource.insertPokemons(listOf(pokemon))
        }
        return checkNotNull(pokemonLocalDataSource.getPokemonByName(name))
    }
     */
}



