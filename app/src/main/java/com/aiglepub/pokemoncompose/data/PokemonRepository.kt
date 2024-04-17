package com.aiglepub.pokemoncompose.data

class PokemonRepository {

    suspend fun fetchAllPokemons(): List<Pokemon> =
        PokemonClient.instance
            .fetchAllPokemons()
            .results
            .map { it.toDomainModel() }
}

private fun RemotePokemon.toDomainModel(): Pokemon {
    val lastSlashIndex = url?.lastIndexOf('/')
    val secondLastSlashIndex = url?.substring(0, lastSlashIndex?.minus(1) ?: 0)?.lastIndexOf('/')
    val pokemonNumber = lastSlashIndex?.let { url?.substring(secondLastSlashIndex?.plus(1) ?: 0, it) }
    if (pokemonNumber != null) {
        return Pokemon(
            id = pokemonNumber.toInt(),
            name = name,
            poster = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$pokemonNumber.png"
        )
    } else {
        return Pokemon(
            id = 0,
            name = "NO EXISTE",
            poster = ""
        )
    }
}
