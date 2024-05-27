package com.aiglepub.pokemoncompose.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


// PARA OBTENERLOS TODOS
@Serializable
data class RemoteResult(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<RemoteSimplePokemon>
)

@Serializable
data class RemoteSimplePokemon(
    val name: String,
    val url: String?
)


//PARA OBTENER SOLO UNO
@Serializable
data class RemoteFullPokemon(
    val abilities: List<Ability>,
    @SerialName("base_experience") val baseExperience: Int,
    val height: Int,
    val id: Int,
    val name: String,
    val sprites: RemoteSprites,
    val stats: List<RemoteStats>,
    val types: List<PokemonType>,
    val weight: Int,
)

@Serializable
data class PokemonType(
    val slot: Int,
    val type: RemoteSimplePokemon
)

@Serializable
data class Ability(
    val ability: RemoteSimplePokemon,
    @SerialName("is_hidden") val isHidden: Boolean,
    val slot: Int
)

@Serializable
data class RemoteSprites (
    @SerialName("back_default") val backDefault: String,
    @SerialName("front_default") val frontDefault: String,
)

@Serializable
data class RemoteStats(
    @SerialName("base_stat") val baseStat: Int,
    val stat: RemoteSimplePokemon
)

