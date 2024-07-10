package com.aiglepub.pokemoncompose.data.datasource.local

import com.aiglepub.pokemoncompose.data.Pokemon
import com.aiglepub.pokemoncompose.data.datasource.local.database.PokemonDao

class PokemonLocalDataSource(private var pokemonDao: PokemonDao) {

    //GET ALL POKEMON
    suspend fun getAllPokemons(): List<Pokemon> = pokemonDao.getAllPokemons()

    //GET POKEMON BY NAME
    suspend fun getPokemonByName(name: String): Pokemon? = pokemonDao.getPokemonByname(name)

    //GET POKEMON COUNT
    suspend fun isEmpty() = pokemonDao.countPokemons() == 0

    //INSERT POKEMONS
    suspend fun insertPokemons(pokemons: List<Pokemon>) = pokemonDao.insertPokemons(pokemons)
}