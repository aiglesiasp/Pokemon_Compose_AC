package com.aiglepub.pokemoncompose.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PokemonDb::class], version = 1, exportSchema = false)
abstract class PokemonDatabase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}