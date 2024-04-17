package com.aiglepub.pokemoncompose.ui.screens.pokemonhome

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiglepub.pokemoncompose.data.Pokemon
import com.aiglepub.pokemoncompose.data.PokemonRepository
import kotlinx.coroutines.launch

class PokemonViewModel: ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    private val repository = PokemonRepository()

    fun onUiReady() {
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(loading = false, pokemons = repository.fetchAllPokemons())
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val pokemons: List<Pokemon> = emptyList()
    )
}