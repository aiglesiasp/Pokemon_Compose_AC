package com.aiglepub.pokemoncompose

import android.app.Application
import androidx.room.Room
import com.aiglepub.pokemoncompose.framework.database.PokemonDatabase

class App : Application() {

    lateinit var db: PokemonDatabase

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(this, PokemonDatabase::class.java, "pokemon-db")
            .fallbackToDestructiveMigration()
            .build()
    }
}