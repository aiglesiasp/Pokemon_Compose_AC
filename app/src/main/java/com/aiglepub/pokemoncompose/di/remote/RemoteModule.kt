package com.aiglepub.pokemoncompose.di.remote

import com.aiglepub.pokemoncompose.data.remote.LocationDataSource
import com.aiglepub.pokemoncompose.data.remote.PokemonRemoteDataSource
import com.aiglepub.pokemoncompose.data.remote.RegionDataSource
import com.aiglepub.pokemoncompose.framework.remote.LocationDataSourceImpl
import com.aiglepub.pokemoncompose.framework.remote.PokemonRemoteDataSourceImpl
import com.aiglepub.pokemoncompose.framework.remote.RegionDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteModule {

    @Binds
    abstract fun bindPokemonRemoteDatSource(impl: PokemonRemoteDataSourceImpl): PokemonRemoteDataSource

    @Binds
    abstract fun bindRegionDataSource(impl: RegionDataSourceImpl): RegionDataSource

    @Binds
    abstract fun bindLocationDataSource(impl: LocationDataSourceImpl): LocationDataSource

}