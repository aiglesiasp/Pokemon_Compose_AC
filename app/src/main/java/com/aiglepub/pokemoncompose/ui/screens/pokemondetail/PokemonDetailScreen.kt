package com.aiglepub.pokemoncompose.ui.screens.pokemondetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.aiglepub.pokemoncompose.domain.entities.Pokemon
import com.aiglepub.pokemoncompose.ui.ScreenAppTheme
import com.aiglepub.pokemoncompose.ui.common.PkScaffold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailScreen(
    vm: PokemonDetailViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val state by vm.state.collectAsState()
    val detailState = rememberPokemonDetailState(state)

    ScreenAppTheme {
        PkScaffold(
            state = state,
            topBar = {
                DetailTopBar(
                    title = detailState.topBarTitle,
                    scrollBehavior = detailState.scrollBehavior,
                    onBack = onBack)
            },
            modifier = Modifier.nestedScroll(detailState.scrollBehavior.nestedScrollConnection),
            contentWindowInsets = WindowInsets.safeDrawing
        ) {paddingValues, pokemon ->
            PokemonDetail(
                pokemon = pokemon,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}

@Composable
private fun PokemonDetail(
    pokemon: Pokemon,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {

        AsyncImage(
            model = pokemon.poster,
            contentDescription = pokemon.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 12f)
        )

        Text(
            text = pokemon.name,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = buildAnnotatedString {
                Property("NAME: ", pokemon.name.uppercase())
                Property("HEIGHT: ", pokemon.height.toString())
                Property("WEIGHT: ", pokemon.weight.toString())
                /*pokemon.stats.forEach {remoteStat ->
                    Property(remoteStat.stat.name.toUpperCase(), remoteStat.baseStat.toString())
                }
                pokemon.types.forEach {pokemonType ->
                    Property("TYPE ${pokemonType.slot.toString().toUpperCase()}", pokemonType.type.name)
                }*/
            },
            modifier = Modifier.fillMaxWidth().background(color = MaterialTheme.colorScheme.secondaryContainer).padding(16.dp)
        )
    }
}

@Composable
private fun AnnotatedString.Builder.Property(name: String, value: String, end: Boolean = false) {
    withStyle(ParagraphStyle(lineHeight = 18.sp)) {
        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
            append("$name: ")
        }
        append(value)
        if (!end) {
            append("\n")
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun DetailTopBar(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior,
    onBack: () -> Unit
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Arrow back"
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}