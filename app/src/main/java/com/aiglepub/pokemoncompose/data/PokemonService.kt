package com.aiglepub.pokemoncompose.data

import retrofit2.http.GET

interface PokemonService {

    @GET("pokemon?limit=1302")
    suspend fun fetchAllPokemons(): RemoteResult
}