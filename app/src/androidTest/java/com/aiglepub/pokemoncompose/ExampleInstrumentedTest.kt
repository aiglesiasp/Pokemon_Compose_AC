package com.aiglepub.pokemoncompose

import androidx.test.rule.GrantPermissionRule
import com.aiglepub.pokemoncompose.domain.PokemonRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class ExampleInstrumentedTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var locationPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    )

    @Inject
    lateinit var repository: PokemonRepository

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun test_it_works() {
        runBlocking {
            val pokemons = repository.pokemons.first()
            assertTrue(pokemons.isEmpty())
        }
    }
}