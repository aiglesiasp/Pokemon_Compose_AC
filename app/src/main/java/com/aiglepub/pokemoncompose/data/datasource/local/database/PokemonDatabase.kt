package com.aiglepub.pokemoncompose.data.datasource.local.database

import androidx.room.Database
import com.aiglepub.pokemoncompose.data.Pokemon

@Database(entities = [Pokemon::class], version = 1, exportSchema = false)
abstract class PokemonDatabase {
    abstract fun pokemonDao(): PokemonDao
}