package com.aiglepub.pokemoncompose.fakes

import com.aiglepub.pokemoncompose.data.local.PokemonLocalDataSource
import com.aiglepub.pokemoncompose.domain.entities.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.map

class FakePokemonLocalDataSource: PokemonLocalDataSource {

    val inMemoryPokemons = MutableStateFlow<List<Pokemon>>(emptyList())

    override val pokemons: Flow<List<Pokemon>> = inMemoryPokemons

    override fun getPokemonByName(name: String): Flow<Pokemon?> =
        inMemoryPokemons.map { it.firstOrNull() { pokemon -> pokemon.name == name } }

    override suspend fun isEmpty(): Boolean {
        return (inMemoryPokemons.count() == 0)
    }

    override suspend fun insertPokemons(pokemons: List<Pokemon>) {
        inMemoryPokemons.value = pokemons
    }
}