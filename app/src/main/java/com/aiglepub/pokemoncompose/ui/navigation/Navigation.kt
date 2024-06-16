package com.aiglepub.pokemoncompose.ui.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.aiglepub.pokemoncompose.data.network.PokemonClient
import com.aiglepub.pokemoncompose.data.PokemonRepository
import com.aiglepub.pokemoncompose.data.RegionRepository
import com.aiglepub.pokemoncompose.data.datasource.LocationDataSource
import com.aiglepub.pokemoncompose.data.datasource.PokemonRemoteDataSource
import com.aiglepub.pokemoncompose.data.datasource.RegionDataSource
import com.aiglepub.pokemoncompose.ui.screens.pokemondetail.PokemonDetailScreen
import com.aiglepub.pokemoncompose.ui.screens.pokemondetail.PokemonDetailViewModel
import com.aiglepub.pokemoncompose.ui.screens.pokemonhome.PokemonScreen
import com.aiglepub.pokemoncompose.ui.screens.pokemonhome.PokemonViewModel

@Composable
fun Navigation() {

    val navController = rememberNavController()
    val pokemonService = PokemonClient.instance
    val aplication = LocalContext.current.applicationContext as Application
    val locationDataSource = LocationDataSource(aplication)
    val regionDataSource = RegionDataSource(aplication, locationDataSource)
    val regionRepository = RegionRepository(regionDataSource)
    val pokemonRemoteDataSource = PokemonRemoteDataSource(pokemonService)
    val pokemonRepository = PokemonRepository(regionRepository, pokemonRemoteDataSource)

    NavHost(navController = navController, startDestination = PokemonHome ) {
        composable<PokemonHome> {
            PokemonScreen(
                vm = viewModel { PokemonViewModel(pokemonRepository) },
                onClick = {pokemon -> navController.navigate(PokemonDetail(pokemon.name)) }
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