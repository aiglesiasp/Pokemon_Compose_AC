package com.aiglepub.pokemoncompose.framework.api

import com.aiglepub.pokemoncompose.data.datasource.remote.PokemonRemoteDataSource
import com.aiglepub.pokemoncompose.domain.entities.Pokemon

class PokemonRemoteDataSourceImpl(
    private val pokemonService: PokemonService
) : PokemonRemoteDataSource {
    override suspend fun fetchAllPokemons(): List<Pokemon> =
        pokemonService
            .fetchAllPokemons()
            .results
            .map { it.toDomainModel() }

    override suspend fun fetchPokemonByName(name: String): Pokemon =
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
            stats = emptyList(),
            types = emptyList()
        )
    } else {
        Pokemon(
            id = 0,
            name = "NO EXISTE",
            poster = "",
            height = 0,
            weight = 0,
            stats = emptyList(),
            types = emptyList()
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
        stats = emptyList(),
        types = emptyList()
    )
}