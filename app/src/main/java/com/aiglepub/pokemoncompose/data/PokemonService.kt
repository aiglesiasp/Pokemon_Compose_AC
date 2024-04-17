package com.aiglepub.pokemoncompose.data

import retrofit2.http.GET

interface PokemonService {

    @GET("/pokemon")
    suspend fun fetchAllPokemons(): RemoteResult
}