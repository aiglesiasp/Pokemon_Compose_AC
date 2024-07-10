package com.aiglepub.pokemoncompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.aiglepub.pokemoncompose.App
import com.aiglepub.pokemoncompose.data.PokemonRepository
import com.aiglepub.pokemoncompose.data.datasource.local.PokemonLocalDataSource
import com.aiglepub.pokemoncompose.data.datasource.remote.PokemonRemoteDataSource
import com.aiglepub.pokemoncompose.data.datasource.remote.network.PokemonClient
import com.aiglepub.pokemoncompose.ui.screens.pokemondetail.PokemonDetailScreen
import com.aiglepub.pokemoncompose.ui.screens.pokemondetail.PokemonDetailViewModel
import com.aiglepub.pokemoncompose.ui.screens.pokemonhome.PokemonScreen
import com.aiglepub.pokemoncompose.ui.screens.pokemonhome.PokemonViewModel

@Composable
fun Navigation() {

    val navController = rememberNavController()
    val pokemonClient = PokemonClient.instance
    val aplication = LocalContext.current.applicationContext as App
    val pokemonRemoteDataSource = PokemonRemoteDataSource(pokemonClient)
    val pokemonLocalDataSource = PokemonLocalDataSource(aplication.db.pokemonDao())
    val pokemonRepository = PokemonRepository(pokemonRemoteDataSource, pokemonLocalDataSource)

    NavHost(navController = navController, startDestination = PokemonHome ) {
        composable<PokemonHome> {
            PokemonScreen(
                vm = viewModel { PokemonViewModel(pokemonRepository) },
                onClick = { pokemon ->
                    navController.navigate(PokemonDetail(pokemon.name))
                }
            )
        }

        composable<PokemonDetail>(
        ) {backStackEntry ->
            val pokemonDetail = backStackEntry.toRoute<PokemonDetail>()
            PokemonDetailScreen(
                vm = viewModel { PokemonDetailViewModel(pokemonDetail.pokemonName, pokemonRepository) },
                onBack = { navController.popBackStack(route = PokemonHome, inclusive = false) }
            )
        }
    }
}