package com.aiglepub.pokemoncompose.data

import com.aiglepub.pokemoncompose.data.datasource.local.PokemonLocalDataSourceImpl
import com.aiglepub.pokemoncompose.data.datasource.remote.PokemonRemoteDataSourceImpl
import com.aiglepub.pokemoncompose.domain.entities.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach

class PokemonRepository(
    private val pokemonRemoteDataSource: PokemonRemoteDataSourceImpl,
    private val pokemonLocalDataSource: PokemonLocalDataSourceImpl
) {

    val pokemons: Flow<List<Pokemon>> = pokemonLocalDataSource.pokemons.onEach { localPokemons ->
        if(localPokemons.isEmpty()) {
            val remotePokemons = pokemonRemoteDataSource.fetchAllPokemons()
            pokemonLocalDataSource.insertPokemons(remotePokemons)
        }
    }

    fun fetchPokemonByName(name: String): Flow<Pokemon> = pokemonLocalDataSource.getPokemonByName(name).onEach { localPokemon ->
        if(localPokemon == null) {
            val remotePokemon = pokemonRemoteDataSource.fetchPokemonByName(name)
            pokemonLocalDataSource.insertPokemons(listOf(remotePokemon))
        }
        if(localPokemon != null && localPokemon.height == 0 && localPokemon.weight == 0) {
            val remotePokemon = pokemonRemoteDataSource.fetchPokemonByName(name)
            updatePokemon(remotePokemon)
        }
    }.filterNotNull()

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



