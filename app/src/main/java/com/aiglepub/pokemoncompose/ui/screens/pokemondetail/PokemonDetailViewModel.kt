package com.aiglepub.pokemoncompose.ui.screens.pokemondetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiglepub.pokemoncompose.data.Pokemon
import com.aiglepub.pokemoncompose.data.PokemonClient
import com.aiglepub.pokemoncompose.data.PokemonRepository
import kotlinx.coroutines.launch

class PokemonDetailViewModel(private val name: String): ViewModel() {

    private val repository = PokemonRepository(PokemonClient.instance)

    var state by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(loading = false, pokemon = repository.fetchPokemonByName(name))
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val pokemon: Pokemon? = null
    )
}