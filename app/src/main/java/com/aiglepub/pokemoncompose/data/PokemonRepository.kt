package com.aiglepub.pokemoncompose.data

import com.aiglepub.pokemoncompose.data.datasource.local.PokemonLocalDataSource
import com.aiglepub.pokemoncompose.data.datasource.remote.PokemonRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.transform

class PokemonRepository(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    private val pokemonLocalDataSource: PokemonLocalDataSource
) {

    val pokemons: Flow<List<Pokemon>> = pokemonLocalDataSource.pokemons.onEach { localPokemons ->
        if(localPokemons.isEmpty()) {
            val remotePokemons = pokemonRemoteDataSource.fetchAllPokemons()
            pokemonLocalDataSource.insertPokemons(remotePokemons)
        }
    }

    fun fetchPokemonByName(name: String): Flow<Pokemon?> = pokemonLocalDataSource.getPokemonByName(name).onEach { localPokemon ->
        if(localPokemon == null) {
            val remotePokemon = pokemonRemoteDataSource.fetchPokemonByName(name)
            pokemonLocalDataSource.insertPokemons(listOf(remotePokemon))
        }
        if(localPokemon != null && localPokemon.height == 0 && localPokemon.weight == 0) {
            val remotePokemon = pokemonRemoteDataSource.fetchPokemonByName(name)
            updatePokemon(remotePokemon)
        }
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



