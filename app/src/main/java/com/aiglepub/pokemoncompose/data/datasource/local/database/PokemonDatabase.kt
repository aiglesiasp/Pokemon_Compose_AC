package com.aiglepub.pokemoncompose.data.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aiglepub.pokemoncompose.data.datasource.local.PokemonDb
import com.aiglepub.pokemoncompose.domain.entities.Pokemon

@Database(entities = [PokemonDb::class], version = 1, exportSchema = false)
abstract class PokemonDatabase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}