package com.aiglepub.pokemoncompose.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aiglepub.pokemoncompose.data.datasource.remote.network.PokemonType
import com.aiglepub.pokemoncompose.data.datasource.remote.network.RemoteSimplePokemon
import com.aiglepub.pokemoncompose.data.datasource.remote.network.RemoteStats

@Entity
data class Pokemon(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val poster: String,
    val height: Int,
    val weight: Int,
    val stats: List<RemoteStats>,
    val types: List<PokemonType>,
)

val pokemonsMock = (1..100).map {
    Pokemon(
        id = it,
        name = "Pokemon $it",
        poster = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$it.png",
        height = (100..200).random(),
        weight = (100..200).random(),
        stats = (1..4).map {
            RemoteStats(
                baseStat = it,
                stat = RemoteSimplePokemon(name = "Stat $it", url = "https://pokeapi.co/api/v2/stat/$it")
            )
        },
        types = (1..2).map {
            PokemonType(
              slot = it,
                type = RemoteSimplePokemon(name = "Type $it", url = "https://pokeapi.co/api/v2/type/$it")
            )
        }
    )
}