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
import com.aiglepub.pokemoncompose.framework.api.PokemonClient
import com.aiglepub.pokemoncompose.domain.usecases.FetchPokemonByNameUseCase
import com.aiglepub.pokemoncompose.domain.usecases.FetchPokemonsUseCase
import com.aiglepub.pokemoncompose.framework.api.PokemonRemoteDataSourceImpl
import com.aiglepub.pokemoncompose.framework.database.PokemonLocalDataSourceImpl
import com.aiglepub.pokemoncompose.ui.screens.pokemondetail.PokemonDetailScreen
import com.aiglepub.pokemoncompose.ui.screens.pokemondetail.PokemonDetailViewModel
import com.aiglepub.pokemoncompose.ui.screens.pokemonhome.PokemonScreen
import com.aiglepub.pokemoncompose.ui.screens.pokemonhome.PokemonViewModel

@Composable
fun Navigation() {

    val navController = rememberNavController()
    val pokemonClient = PokemonClient.instance
    val aplication = LocalContext.current.applicationContext as App
    val pokemonRemoteDataSource = PokemonRemoteDataSourceImpl(pokemonClient)
    val pokemonLocalDataSource = PokemonLocalDataSourceImpl(aplication.db.pokemonDao())
    val pokemonRepository = PokemonRepository(pokemonRemoteDataSource, pokemonLocalDataSource)
    val fetchPokemonsUseCase = FetchPokemonsUseCase(pokemonRepository)
    val fetchPokemonByNameUseCase = FetchPokemonByNameUseCase(pokemonRepository)

    NavHost(navController = navController, startDestination = PokemonHome ) {
        composable<PokemonHome> {
            PokemonScreen(
                vm = viewModel { PokemonViewModel(fetchPokemonsUseCase) },
                onClick = { pokemon ->
                    navController.navigate(PokemonDetail(pokemon.name))
                }
            )
        }

        composable<PokemonDetail>(
        ) {backStackEntry ->
            val pokemonDetail = backStackEntry.toRoute<PokemonDetail>()
            PokemonDetailScreen(
                vm = viewModel { PokemonDetailViewModel(pokemonDetail.pokemonName, fetchPokemonByNameUseCase) },
                onBack = { navController.popBackStack(route = PokemonHome, inclusive = false) }
            )
        }
    }
}