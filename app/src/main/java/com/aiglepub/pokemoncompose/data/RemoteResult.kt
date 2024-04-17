package com.aiglepub.pokemoncompose.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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

@Serializable
data class RemoteFullPokemon(
    val id: Int,
    val name: String,
    val stats: List<RemoteStats>,
    val sprites: RemoteSprites
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

