package com.aiglepub.pokemoncompose.data

import kotlinx.serialization.Serializable

@Serializable
data class RemoteResult(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<RemotePokemon>
)

@Serializable
data class RemotePokemon(
    val name: Int,
    val url: String
)