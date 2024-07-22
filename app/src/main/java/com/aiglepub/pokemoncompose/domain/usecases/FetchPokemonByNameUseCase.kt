package com.aiglepub.pokemoncompose.domain.usecases

import com.aiglepub.pokemoncompose.data.Pokemon
import com.aiglepub.pokemoncompose.data.PokemonRepository
import kotlinx.coroutines.flow.Flow

class FetchPokemonByNameUseCase (
    private val pokemonRepository: PokemonRepository
) {
    operator fun invoke(name: String): Flow<Pokemon> = pokemonRepository.fetchPokemonByName(name)
}