package com.aiglepub.pokemoncompose.ui.screens.pokemonhome

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.aiglepub.pokemoncompose.Result
import com.aiglepub.pokemoncompose.helpers.generateListPokemons
import com.aiglepub.pokemoncompose.ui.common.LOADING_INDICATOR_TAG
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class PokemonScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenLoadingState_showProgress(): Unit = with(composeTestRule) {
        setContent {
            PokemonScreen(
                state = Result.Loading,
                onClick = {}
            )
        }

        onNodeWithTag(LOADING_INDICATOR_TAG).assertIsDisplayed()
    }

    @Test
    fun whenErrorState_showErrorMessage(): Unit = with(composeTestRule) {
        setContent {
            PokemonScreen(
                state = Result.Error(RuntimeException("An error ocurred")),
                onClick = {}
            )
        }

        onNodeWithText("An error ocurred").assertExists()
    }

    @Test
    fun whenSuccessState_showPokemons(): Unit = with(composeTestRule) {
        setContent {
            PokemonScreen(
                state = Result.Success(generateListPokemons(1,2,3)),
                onClick = {}
            )
        }

        onNodeWithText("Name 2").assertExists()
    }

    @Test
    fun whenPokemonClicked_showDetail(): Unit = with(composeTestRule) {
        var pokemonClicked = -1
        val pokemons = generateListPokemons(1,2,3)
        setContent {
            PokemonScreen(
                state = Result.Success(pokemons),
                onClick = { pokemonClicked = it.id }
            )
        }

        onNodeWithText("Name 2").performClick()

        assertEquals(2, pokemonClicked)
    }
}