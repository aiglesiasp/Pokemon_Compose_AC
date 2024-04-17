package com.aiglepub.pokemoncompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aiglepub.pokemoncompose.ui.screens.pokemonhome.PokemonScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "pokemonHome" ) {
        composable("pokemonHome") {
            PokemonScreen()
        }


    }
}