package com.aiglepub.pokemoncompose.data

data class Pokemon(
    val id: Int,
    val name: String,
    val poster: String
)

val pokemonsMock = (1..100).map {
    Pokemon(
        id = it,
        name = "Pokemon $it",
        poster = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$it.png"
    )
}