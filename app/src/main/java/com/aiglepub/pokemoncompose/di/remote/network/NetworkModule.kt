package com.aiglepub.pokemoncompose.di.remote.network

import android.content.Context
import android.location.Geocoder
import com.aiglepub.pokemoncompose.framework.remote.api.PokemonClient
import com.aiglepub.pokemoncompose.framework.remote.api.PokemonService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun providePokemonClient(): PokemonService = PokemonClient().instance

    @Provides
    fun provideFusedLocationproviderClient(@ApplicationContext context: Context): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }

    @Provides
    fun provideGeocoder(@ApplicationContext context: Context): Geocoder {
        return Geocoder(context)
    }
}