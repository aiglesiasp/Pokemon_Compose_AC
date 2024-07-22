package com.aiglepub.pokemoncompose.data.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonDb(
    @PrimaryKey
    val id: Int,
    val name: String,
    val poster: String,
    val height: Int,
    val weight: Int,
    //val stats: List<StatsDb>,
    //val types: List<TypeDb>,
)

data class StatsDb(
    val baseStat: Int,
    val stat: SimplePokemonDb
)

data class TypeDb(
    val slot: Int,
    val type: SimplePokemonDb
)

data class SimplePokemonDb(
    val name: String,
    val url: String?
)