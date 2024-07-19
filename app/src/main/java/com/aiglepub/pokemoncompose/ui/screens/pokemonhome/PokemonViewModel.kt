package com.aiglepub.pokemoncompose.ui.screens.pokemonhome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiglepub.pokemoncompose.data.Pokemon
import com.aiglepub.pokemoncompose.data.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val repository: PokemonRepository
): ViewModel() {

    val state: StateFlow<UiState> = repository.pokemons
        .map { UiState(pokemons = it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState(loading = true)
        )

    fun onUiReady() {
        /*viewModelScope.launch {
            _state.value = UiState(loading = true)
            repository.pokemons.collect { listPokemons ->
                _state.value = UiState(loading = false, pokemons = listPokemons)
            }
            //_state.value = UiState(loading = false, pokemons = repository.fetchAllPokemons())
        }

         */
    }

    data class UiState(
        val loading: Boolean = false,
        val pokemons: List<Pokemon> = emptyList()
    )
}