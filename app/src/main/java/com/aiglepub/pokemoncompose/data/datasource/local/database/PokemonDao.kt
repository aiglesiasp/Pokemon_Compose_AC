package com.aiglepub.pokemoncompose.data.datasource.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aiglepub.pokemoncompose.data.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query("SELECT * FROM Pokemon")
    fun getAllPokemons(): Flow<List<Pokemon>>

    @Query("SELECT * FROM Pokemon WHERE name = :name")
    fun getPokemonByname(name: String): Flow<Pokemon?>

    @Query("SELECT COUNT() FROM Pokemon")
    suspend fun countPokemons(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemons(pokemons: List<Pokemon>)
}