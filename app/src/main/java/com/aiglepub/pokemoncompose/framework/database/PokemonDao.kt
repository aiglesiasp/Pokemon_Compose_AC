package com.aiglepub.pokemoncompose.framework.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query("SELECT * FROM PokemonDb")
    fun getAllPokemons(): Flow<List<PokemonDb>>

    @Query("SELECT * FROM PokemonDb WHERE name = :name")
    fun getPokemonByname(name: String): Flow<PokemonDb?>

    @Query("SELECT COUNT() FROM PokemonDb")
    suspend fun countPokemons(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemons(pokemons: List<PokemonDb>)
}