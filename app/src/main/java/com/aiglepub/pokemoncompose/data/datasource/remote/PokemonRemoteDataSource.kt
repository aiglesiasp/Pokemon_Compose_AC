package com.aiglepub.pokemoncompose.data.datasource.remote

import com.aiglepub.pokemoncompose.data.Pokemon
import com.aiglepub.pokemoncompose.data.datasource.remote.network.PokemonService
import com.aiglepub.pokemoncompose.data.datasource.remote.network.RemoteFullPokemon
import com.aiglepub.pokemoncompose.data.datasource.remote.network.RemoteSimplePokemon

class PokemonRemoteDataSource(
    private val pokemonService: PokemonService
) {
    suspend fun fetchAllPokemons(): List<Pokemon> =
        pokemonService
            .fetchAllPokemons()
            .results
            .map { it.toDomainModel() }

    suspend fun fetchPokemonByName(name: String): Pokemon =
        pokemonService
            .fetchPokemonByName(name)
            .toDomainModel()
}

private fun RemoteSimplePokemon.toDomainModel(): Pokemon {
    val lastSlashIndex = url?.lastIndexOf('/')
    val secondLastSlashIndex = url?.substring(0, lastSlashIndex?.minus(1) ?: 0)?.lastIndexOf('/')
    val pokemonNumber = lastSlashIndex?.let { url?.substring(secondLastSlashIndex?.plus(1) ?: 0, it) }
    return if (pokemonNumber != null) {
        Pokemon(
            id = pokemonNumber.toInt(),
            name = name,
            poster = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$pokemonNumber.png",
            height = 0,
            weight = 0,
            //stats = emptyList(),
            //types = emptyList()
        )
    } else {
        Pokemon(
            id = 0,
            name = "NO EXISTE",
            poster = "",
            height = 0,
            weight = 0,
            //stats = emptyList(),
            //types = emptyList()
        )
    }
}

private fun RemoteFullPokemon.toDomainModel(): Pokemon {
    return Pokemon(
        id = id,
        name = name,
        poster = sprites.frontDefault,
        height = height,
        weight = weight,
        //stats = stats,
        //types = types
    )
}