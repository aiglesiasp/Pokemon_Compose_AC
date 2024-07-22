package com.aiglepub.pokemoncompose.ui.screens.pokemondetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiglepub.pokemoncompose.Result
import com.aiglepub.pokemoncompose.domain.entities.Pokemon
import com.aiglepub.pokemoncompose.domain.usecases.FetchPokemonByNameUseCase
import com.aiglepub.pokemoncompose.stateAsResultIn
import kotlinx.coroutines.flow.StateFlow

class PokemonDetailViewModel(
    name: String,
    fetchPokemonByNameUseCase: FetchPokemonByNameUseCase,
): ViewModel() {

    val state: StateFlow<Result<Pokemon>> = fetchPokemonByNameUseCase(name)
        .stateAsResultIn(viewModelScope)

}