package com.aiglepub.pokemoncompose.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pokemon(
    //@PrimaryKey(autoGenerate = true)
    @PrimaryKey val id: Int,
    val name: String,
    val poster: String,
    val height: Int,
    val weight: Int,
    //val stats: List<RemoteStats>,
    //val types: List<PokemonType>,
)