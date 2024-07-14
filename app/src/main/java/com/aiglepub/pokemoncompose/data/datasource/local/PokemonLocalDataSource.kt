package com.aiglepub.pokemoncompose.data.datasource.local

import com.aiglepub.pokemoncompose.data.Pokemon
import com.aiglepub.pokemoncompose.data.datasource.local.database.PokemonDao
import kotlinx.coroutines.flow.Flow

class PokemonLocalDataSource(private var pokemonDao: PokemonDao) {

    //GET ALL POKEMON
    val pokemons = pokemonDao.getAllPokemons()

    //GET POKEMON BY NAME
    fun getPokemonByName(name: String): Flow<Pokemon?> = pokemonDao.getPokemonByname(name)

    //GET POKEMON COUNT
    suspend fun isEmpty() = pokemonDao.countPokemons() == 0

    //INSERT POKEMONS
    suspend fun insertPokemons(pokemons: List<Pokemon>) = pokemonDao.insertPokemons(pokemons)
}