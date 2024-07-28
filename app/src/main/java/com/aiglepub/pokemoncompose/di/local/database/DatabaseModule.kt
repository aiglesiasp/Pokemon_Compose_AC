package com.aiglepub.pokemoncompose.di.local.database

import android.content.Context
import androidx.room.Room
import com.aiglepub.pokemoncompose.framework.database.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PokemonDatabase {
        return Room.databaseBuilder(context, PokemonDatabase::class.java, "pokemon-db").build()
    }

    @Provides
    fun providePokemonDao(database: PokemonDatabase) = database.pokemonDao()

}