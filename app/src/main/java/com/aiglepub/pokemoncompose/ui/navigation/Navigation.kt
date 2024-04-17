package com.aiglepub.pokemoncompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aiglepub.pokemoncompose.ui.screens.pokemondetail.PokemonDetailScreen
import com.aiglepub.pokemoncompose.ui.screens.pokemondetail.PokemonDetailViewModel
import com.aiglepub.pokemoncompose.ui.screens.pokemonhome.PokemonScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "pokemonHome" ) {
        composable("pokemonHome") {
            PokemonScreen(onClick = {pokemon ->
                navController.navigate("pokemonDetail/${pokemon.name}")
            })
        }

        composable(
            route = "pokemonDetail/{pokemonName}",
            arguments = listOf(navArgument("pokemonName") { type = NavType.StringType})
        ) {backStackEntry ->
            val pokemonName = backStackEntry.arguments?.getString("pokemonName")
            requireNotNull(pokemonName)
            PokemonDetailScreen(
                vm = viewModel { PokemonDetailViewModel(pokemonName) },
                onBack = { navController.popBackStack() }
            )
        }
    }
}