package com.aiglepub.pokemoncompose.ui.screens.pokemonhome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiglepub.pokemoncompose.Result
import com.aiglepub.pokemoncompose.data.Pokemon
import com.aiglepub.pokemoncompose.data.PokemonRepository
import com.aiglepub.pokemoncompose.stateAsResultIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest

class PokemonViewModel(repository: PokemonRepository): ViewModel() {

    private val uiReadyState = MutableStateFlow(false)

    @OptIn(ExperimentalCoroutinesApi::class)
    val state: StateFlow<Result<List<Pokemon>>> = uiReadyState
        .filter { it }
        .flatMapLatest {  repository.pokemons }
        .stateAsResultIn(viewModelScope)



    fun onUiReady() {
        uiReadyState.value = true
    }
}