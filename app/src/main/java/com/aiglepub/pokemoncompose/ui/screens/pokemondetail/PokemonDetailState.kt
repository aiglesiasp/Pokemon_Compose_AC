package com.aiglepub.pokemoncompose.ui.screens.pokemondetail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

@OptIn(ExperimentalMaterial3Api::class)
class PokemonDetailState(
    val scrollBehavior: TopAppBarScrollBehavior,
    //val snackbarHostState: SnackbarHostState
) {

    @Composable
    fun ShowMessageEffect(message: String?, onMessageShown: () -> Unit) {
        LaunchedEffect(message) {
            message?.let {
                //snackbarHostState.currentSnackbarData?.dismiss()
                //snackbarHostState.showSnackbar(it)
                onMessageShown()
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberPokemonDetailState(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    //snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
): PokemonDetailState {
    return remember(scrollBehavior) { PokemonDetailState(scrollBehavior) }
}