package com.aiglepub.pokemoncompose.ui.screens.pokemondetail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.aiglepub.pokemoncompose.domain.entities.Pokemon
import com.aiglepub.pokemoncompose.Result

@OptIn(ExperimentalMaterial3Api::class)
class PokemonDetailState(
    private val state: Result<Pokemon>,
    val scrollBehavior: TopAppBarScrollBehavior,
) {

    val pokemon: Pokemon?
        get() = (state as? Result.Success)?.data

    val topBarTitle: String
        get() = pokemon?.name ?: ""

    @Composable
    fun ShowMessageEffect(message: String?, onMessageShown: () -> Unit) {
        LaunchedEffect(message) {
            message?.let {
                onMessageShown()
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberPokemonDetailState(
    state: Result<Pokemon>,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    //snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
): PokemonDetailState {
    return remember(state, scrollBehavior) { PokemonDetailState(state, scrollBehavior) }
}