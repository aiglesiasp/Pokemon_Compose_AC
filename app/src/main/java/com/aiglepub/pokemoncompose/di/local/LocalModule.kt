package com.aiglepub.pokemoncompose.di.local

import com.aiglepub.pokemoncompose.data.local.PokemonLocalDataSource
import com.aiglepub.pokemoncompose.framework.local.PokemonLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalModule {

    @Binds
    abstract fun bindPokemonLocalDataSource(impl: PokemonLocalDataSourceImpl): PokemonLocalDataSource
}