package com.aiglepub.pokemoncompose.domain.usecases

import com.aiglepub.pokemoncompose.data.Pokemon
import com.aiglepub.pokemoncompose.data.PokemonRepository
import kotlinx.coroutines.flow.Flow

class FetchPokemonsUseCase(
    private val pokemonRepository: PokemonRepository
) {
    operator fun invoke(): Flow<List<Pokemon>> = pokemonRepository.pokemons
}