package com.aiglepub.pokemoncompose.helpers

import com.aiglepub.pokemoncompose.domain.entities.Pokemon

fun generateSamplePokemon(id: Int) = Pokemon(
    id = id,
    name = "Name $id",
    poster = "Poster $id",
    height = id,
    weight = id,
    stats = emptyList(),
    types = emptyList()
)

fun generateListPokemons(vararg ids: Int) = ids.map { generateSamplePokemon(it) }