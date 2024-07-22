package com.aiglepub.pokemoncompose.ui.screens.pokemonhome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiglepub.pokemoncompose.Result
import com.aiglepub.pokemoncompose.domain.entities.Pokemon
import com.aiglepub.pokemoncompose.domain.usecases.FetchPokemonsUseCase
import com.aiglepub.pokemoncompose.stateAsResultIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest

class PokemonViewModel(private val fetchPokemonsUseCase: FetchPokemonsUseCase): ViewModel() {

    private val uiReadyState = MutableStateFlow(false)

    @OptIn(ExperimentalCoroutinesApi::class)
    val state: StateFlow<Result<List<Pokemon>>> = uiReadyState
        .filter { it }
        .flatMapLatest {  fetchPokemonsUseCase() }
        .stateAsResultIn(viewModelScope)

    fun onUiReady() {
        uiReadyState.value = true
    }
}