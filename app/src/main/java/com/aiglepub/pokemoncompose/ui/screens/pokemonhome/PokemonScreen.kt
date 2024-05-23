package com.aiglepub.pokemoncompose.ui.screens.pokemonhome

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.aiglepub.pokemoncompose.R
import com.aiglepub.pokemoncompose.data.Pokemon
import com.aiglepub.pokemoncompose.ui.ScreenAppTheme
import com.aiglepub.pokemoncompose.ui.common.LoadingProgressIndicator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonScreen(vm: PokemonViewModel = viewModel(), onClick: (Pokemon) -> Unit) {

    vm.onUiReady()

    ScreenAppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.app_name)) } )
            }
        ) { paddingValues ->

            val state by vm.state.collectAsState()

            if(state.loading) {
                LoadingProgressIndicator(modifier = Modifier.padding(paddingValues))
            }

            LazyVerticalGrid(
                columns = GridCells.Adaptive(120.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(horizontal = 4.dp),
                contentPadding = paddingValues
            ) {
                items(state.pokemons) {pokemon ->
                    PokemonItem(
                        pokemon = pokemon,
                        onClick = { onClick(pokemon) }
                    )
                }
            }
        }
    }
}



@Composable
fun PokemonItem(pokemon: Pokemon, onClick: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .clip(MaterialTheme.shapes.medium)
            .border(1.dp, Color.LightGray, MaterialTheme.shapes.medium),
            //.shadow(elevation = 8.dp, spotColor = Color.Black),
        onClick = onClick
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = pokemon.poster,
                contentDescription = pokemon.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2 / 3f)
                    .clip(MaterialTheme.shapes.medium)
            )
            Text(
                text = pokemon.name,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
