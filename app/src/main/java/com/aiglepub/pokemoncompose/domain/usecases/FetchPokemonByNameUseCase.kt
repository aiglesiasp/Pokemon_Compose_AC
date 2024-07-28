package com.aiglepub.pokemoncompose.domain.usecases

import com.aiglepub.pokemoncompose.domain.PokemonRepository
import com.aiglepub.pokemoncompose.domain.entities.Pokemon
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchPokemonByNameUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    operator fun invoke(name: String): Flow<Pokemon> = pokemonRepository.fetchPokemonByName(name)
}