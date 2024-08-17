package com.aiglepub.pokemoncompose.ui.screens.pokemondetail

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.aiglepub.pokemoncompose.Result
import com.aiglepub.pokemoncompose.helpers.generateSamplePokemon
import com.aiglepub.pokemoncompose.ui.common.LOADING_INDICATOR_TAG
import org.junit.Rule
import org.junit.Test

class PokemonDetailScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenLoadingState_showProgress(): Unit = with(composeTestRule) {
        setContent {
            PokemonDetailScreen(
                state = Result.Loading,
                onBack = {},
            )
        }
        onNodeWithTag(LOADING_INDICATOR_TAG).assertIsDisplayed()
    }

    @Test
    fun whenErrorState_showErrorMessage(): Unit = with(composeTestRule) {
        setContent {
            PokemonDetailScreen(
                state = Result.Error(RuntimeException("An error ocurred")),
                onBack = {},
            )
        }
        onNodeWithText("An error ocurred").assertExists()
    }

    @Test
    fun whenSuccessState_showPokemon(): Unit = with(composeTestRule) {
        setContent {
            PokemonDetailScreen(
                state = Result.Success(generateSamplePokemon(1)),
                onBack = {},
            )
        }
        onNodeWithText("Name 1").assertExists()
    }
}