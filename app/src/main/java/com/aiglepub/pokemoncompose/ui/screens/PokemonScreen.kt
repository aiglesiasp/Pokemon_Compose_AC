package com.aiglepub.pokemoncompose.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.aiglepub.pokemoncompose.data.Pokemon
import com.aiglepub.pokemoncompose.data.pokemonsMock
import com.aiglepub.pokemoncompose.ui.ScreenAppTheme
import com.aiglepub.pokemoncompose.ui.screens.scaffoldcomponents.ScaffoldTopBar

@Composable
fun PokemonScreen() {
    ScreenAppTheme {
        Scaffold(
            topBar = { ScaffoldTopBar() }
        ) { paddingValues ->
            LazyVerticalGrid(
                columns = GridCells.Adaptive(120.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(horizontal = 4.dp),
                contentPadding = paddingValues
            ) {
                items(pokemonsMock) {pokemon ->
                    PokemonItem(pokemon = pokemon)
                }
            }
        }
    }
}

@Composable
fun PokemonItem(pokemon: Pokemon) {
    Column(
        modifier = Modifier.clickable(onClick = {  })
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
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PokemonScreen_Preview() {
    PokemonScreen()
}