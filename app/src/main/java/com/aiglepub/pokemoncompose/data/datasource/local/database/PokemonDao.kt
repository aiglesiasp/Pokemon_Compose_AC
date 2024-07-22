package com.aiglepub.pokemoncompose.data.datasource.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aiglepub.pokemoncompose.data.datasource.local.PokemonDb
import com.aiglepub.pokemoncompose.domain.entities.Pokemon
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