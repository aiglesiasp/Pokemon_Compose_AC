package com.aiglepub.pokemoncompose.ui.screens.scaffoldcomponents

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.aiglepub.pokemoncompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldTopBar() {
    TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) })
}

@Preview(widthDp = 400, heightDp = 200, showBackground = true)
@Composable
private fun ScaffoldTopBar_Preview() {
    ScaffoldTopBar()
}