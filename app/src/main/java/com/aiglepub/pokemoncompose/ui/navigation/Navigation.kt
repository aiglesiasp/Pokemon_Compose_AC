package com.aiglepub.pokemoncompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.aiglepub.pokemoncompose.ui.screens.pokemondetail.PokemonDetailScreen
import com.aiglepub.pokemoncompose.ui.screens.pokemondetail.PokemonDetailViewModel
import com.aiglepub.pokemoncompose.ui.screens.pokemonhome.PokemonScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = PokemonHome ) {
        composable<PokemonHome> {
            PokemonScreen(onClick = {pokemon ->
                navController.navigate(PokemonDetail(pokemon.name))
            })
        }

        composable<PokemonDetail>(
        ) {backStackEntry ->
            val pokemonDetail = backStackEntry.toRoute<PokemonDetail>()
            PokemonDetailScreen(
                vm = viewModel { PokemonDetailViewModel(pokemonDetail.pokemonName) },
                onBack = { navController.popBackStack(route = PokemonHome, inclusive = false) }
            )
        }
    }
}