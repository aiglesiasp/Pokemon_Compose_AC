package com.aiglepub.pokemoncompose.data

import com.aiglepub.pokemoncompose.data.local.PokemonLocalDataSource
import com.aiglepub.pokemoncompose.data.remote.PokemonRemoteDataSource
import com.aiglepub.pokemoncompose.domain.PokemonRepository
import com.aiglepub.pokemoncompose.domain.entities.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    private val pokemonLocalDataSource: PokemonLocalDataSource
) : PokemonRepository {

    override val pokemons: Flow<List<Pokemon>>
        get() = pokemonLocalDataSource.pokemons.onEach { localPokemons ->
        if(localPokemons.isEmpty()) {
            val remotePokemons = pokemonRemoteDataSource.fetchAllPokemons()
            pokemonLocalDataSource.insertPokemons(remotePokemons)
        }
    }

    override fun fetchPokemonByName(name: String): Flow<Pokemon> = pokemonLocalDataSource.getPokemonByName(name).onEach { localPokemon ->
        if(localPokemon == null) {
            val remotePokemon = pokemonRemoteDataSource.fetchPokemonByName(name)
            pokemonLocalDataSource.insertPokemons(listOf(remotePokemon))
        }
        if(localPokemon != null && localPokemon.height == 0 && localPokemon.weight == 0) {
            val remotePokemon = pokemonRemoteDataSource.fetchPokemonByName(name)
            updatePokemon(remotePokemon)
        }
    }.filterNotNull()

    override suspend fun updatePokemon(pokemon: Pokemon) {
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



