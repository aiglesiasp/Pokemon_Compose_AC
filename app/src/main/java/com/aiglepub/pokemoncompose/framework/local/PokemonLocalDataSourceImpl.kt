package com.aiglepub.pokemoncompose.framework.local

import com.aiglepub.pokemoncompose.data.local.PokemonLocalDataSource
import com.aiglepub.pokemoncompose.domain.entities.Pokemon
import com.aiglepub.pokemoncompose.framework.local.database.PokemonDao
import com.aiglepub.pokemoncompose.framework.local.database.PokemonDb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonLocalDataSourceImpl @Inject constructor(private var pokemonDao: PokemonDao) : PokemonLocalDataSource {

    //GET ALL POKEMON
    override val pokemons: Flow<List<Pokemon>> = pokemonDao.getAllPokemons().map { it.toDomainPokemon() }

    //GET POKEMON BY NAME
    override fun getPokemonByName(name: String): Flow<Pokemon?> = pokemonDao.getPokemonByname(name).map { it?.toDomainPokemon() }

    //GET POKEMON COUNT
    override suspend fun isEmpty() = pokemonDao.countPokemons() == 0

    //INSERT POKEMONS
    override suspend fun insertPokemons(pokemons: List<Pokemon>) = pokemonDao.insertPokemons(pokemons.toDbPokemon())

}

private fun PokemonDb.toDomainPokemon(): Pokemon {
    return Pokemon(
        id = id,
        name = name,
        poster = poster,
        height = height,
        weight = weight,
        stats = emptyList(),
        types = emptyList()
    )
}

private fun List<PokemonDb>.toDomainPokemon(): List<Pokemon> {
    return map { it.toDomainPokemon() }
}

private fun Pokemon.toDbPokemon(): PokemonDb {
    return PokemonDb(
        id = id,
        name = name,
        poster = poster,
        height = height,
        weight = weight
    )
}

private fun List<Pokemon>.toDbPokemon(): List<PokemonDb> {
    return map { it.toDbPokemon() }
}

