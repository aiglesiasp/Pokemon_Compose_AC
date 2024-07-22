package com.aiglepub.pokemoncompose.domain.entities

data class Pokemon(
    val id: Int,
    val name: String,
    val poster: String,
    val height: Int,
    val weight: Int,
    val stats: List<Stats>,
    val types: List<Type>,
)

data class Stats(
    val baseStat: Int,
    val stat: SimplePokemon
)

data class Type(
    val slot: Int,
    val type: SimplePokemon
)

data class SimplePokemon(
    val name: String,
    val url: String?
)