package com.aiglepub.pokemoncompose.ui.screens.pokemondetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiglepub.pokemoncompose.Result
import com.aiglepub.pokemoncompose.data.Pokemon
import com.aiglepub.pokemoncompose.data.PokemonRepository
import com.aiglepub.pokemoncompose.stateAsResultIn
import kotlinx.coroutines.flow.StateFlow

class PokemonDetailViewModel(
    name: String,
    repository: PokemonRepository
): ViewModel() {

    val state: StateFlow<Result<Pokemon>> = repository.fetchPokemonByName(name)
        .stateAsResultIn(viewModelScope)

}