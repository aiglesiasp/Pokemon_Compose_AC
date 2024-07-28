package com.aiglepub.pokemoncompose.di.repository

import com.aiglepub.pokemoncompose.data.PokemonRepositoryImpl
import com.aiglepub.pokemoncompose.data.RegionRepositoryImpl
import com.aiglepub.pokemoncompose.domain.PokemonRepository
import com.aiglepub.pokemoncompose.domain.RegionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRegionRepository(impl: RegionRepositoryImpl): RegionRepository

    @Binds
    abstract fun bindPokemonRepository(impl: PokemonRepositoryImpl): PokemonRepository


}