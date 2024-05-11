package com.aiglepub.pokemoncompose.ui.screens.pokemondetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.aiglepub.pokemoncompose.ui.ScreenAppTheme
import com.aiglepub.pokemoncompose.ui.common.LoadingProgressIndicator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailScreen(vm: PokemonDetailViewModel = viewModel(), onBack: () -> Unit) {
    val state = vm.state

    ScreenAppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = state.pokemon?.name?.uppercase() ?: "") },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Arrow back" )
                        }
                    }
                )
            }
        ) {paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                if(state.loading) {
                    LoadingProgressIndicator(modifier = Modifier.padding(paddingValues))
                }
                AsyncImage(
                    model = state.pokemon?.poster,
                    contentDescription = state.pokemon?.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16 / 12f)
                )
                state.pokemon?.name?.let {
                    Text(
                        text = it,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }


            }
        }
    }

}