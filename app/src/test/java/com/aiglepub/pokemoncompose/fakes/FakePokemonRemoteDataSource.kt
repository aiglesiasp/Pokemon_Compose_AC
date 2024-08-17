package com.aiglepub.pokemoncompose.fakes

import com.aiglepub.pokemoncompose.data.remote.PokemonRemoteDataSource
import com.aiglepub.pokemoncompose.domain.entities.Pokemon
import com.aiglepub.pokemoncompose.helpers.generateListPokemons

class FakePokemonRemoteDataSource: PokemonRemoteDataSource {

    var pokemons = generateListPokemons(1,2,3,4)

    override suspend fun fetchAllPokemons(): List<Pokemon> = pokemons

    override suspend fun fetchPokemonByName(name: String): Pokemon = pokemons.first { it.name == name }
}