package com.aiglepub.pokemoncompose.framework.remote.api

import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon?limit=1302")
    suspend fun fetchAllPokemons(): RemoteResult

    @GET("pokemon/{pokemonName}")
    suspend fun fetchPokemonByName(@Path("pokemonName") name: String): RemoteFullPokemon
}