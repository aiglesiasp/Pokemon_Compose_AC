package com.aiglepub.pokemoncompose.helpers

import com.aiglepub.pokemoncompose.data.PokemonRepositoryImpl
import com.aiglepub.pokemoncompose.domain.PokemonRepository
import com.aiglepub.pokemoncompose.domain.entities.Pokemon
import com.aiglepub.pokemoncompose.domain.usecases.FetchPokemonsUseCase
import com.aiglepub.pokemoncompose.fakes.FakePokemonLocalDataSource
import com.aiglepub.pokemoncompose.fakes.FakePokemonRemoteDataSource
import com.aiglepub.pokemoncompose.ui.screens.pokemonhome.PokemonViewModel

fun buildPokemonRepositoryWith(
    localData: List<Pokemon> = emptyList(),
    remoteData: List<Pokemon> = emptyList(),
): PokemonRepository {
    val localDataSource = FakePokemonLocalDataSource().apply { inMemoryPokemons.value = localData }
    val remoteDataSource = FakePokemonRemoteDataSource().apply { pokemons = remoteData }
    val pokemonRepository = PokemonRepositoryImpl(remoteDataSource, localDataSource)
    return pokemonRepository
}

fun buildPokemonViewModelWith(
    localData: List<Pokemon> = emptyList(),
    remoteData: List<Pokemon> = emptyList()
): PokemonViewModel {
    val repository = buildPokemonRepositoryWith(localData = localData, remoteData = remoteData)
    val useCase = FetchPokemonsUseCase(repository)
    return PokemonViewModel(useCase)
}