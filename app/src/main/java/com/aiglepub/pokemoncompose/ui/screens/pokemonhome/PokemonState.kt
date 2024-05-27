package com.aiglepub.pokemoncompose.ui.screens.pokemonhome

import android.Manifest
import android.content.Context
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch
import com.aiglepub.pokemoncompose.ui.common.PermissionRequestEffect
import com.aiglepub.pokemoncompose.ui.common.getRegion


class PokemonState @OptIn(ExperimentalMaterial3Api::class) constructor(
    val scrollBehavior: TopAppBarScrollBehavior
) {

    /// PARA COMPROBAR SI SE TIENE PERMISOS DE LOCALIZACION
    @Composable
    fun AskRegionEffect(onRegion: (String) -> Unit) {
        val ctx: Context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()

        PermissionRequestEffect(permission = Manifest.permission.ACCESS_COARSE_LOCATION) { granted ->
            coroutineScope.launch {
                val region = if(granted) ctx.getRegion() else "US"
                onRegion(region)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberPokemonState(
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
): PokemonState {
    return remember(scrollBehavior) { PokemonState(scrollBehavior) }
}