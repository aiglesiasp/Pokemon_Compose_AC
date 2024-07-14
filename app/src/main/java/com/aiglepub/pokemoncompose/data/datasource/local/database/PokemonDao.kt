package com.aiglepub.pokemoncompose.data.datasource.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aiglepub.pokemoncompose.data.Pokemon

@Dao
interface PokemonDao {

    @Query("SELECT * FROM Pokemon")
    suspend fun getAllPokemons(): List<Pokemon>

    @Query("SELECT * FROM Pokemon WHERE name = :name")
    suspend fun getPokemonByname(name: String): Pokemon?

    @Query("SELECT COUNT() FROM Pokemon")
    suspend fun countPokemons(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemons(pokemons: List<Pokemon>)
}