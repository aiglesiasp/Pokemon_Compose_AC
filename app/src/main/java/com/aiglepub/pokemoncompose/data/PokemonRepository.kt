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

    fun fetchPokemonByName(name: String): Flow<Pokemon?> = pokemonLocalDataSource.getPokemonByName(name).transform { localPokemon ->
        val pokemonRemote = pokemonRemoteDataSource.fetchPokemonByName(name)
        if(localPokemon != null) {
            updatePokemon(pokemonRemote)
        } else {
            pokemonLocalDataSource.insertPokemons(listOf(pokemonRemote))
        }
        emit(pokemonRemote)
    }

    suspend fun updatePokemon(pokemon: Pokemon) {
        pokemonLocalDataSource.insertPokemons(
            listOf(pokemon.copy(
                id = pokemon.id,
                name = pokemon.name,
                poster = pokemon.poster,
                height = pokemon.height,
                weight = pokemon.weight)
            )
        )
    }
}



