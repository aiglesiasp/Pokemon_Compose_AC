package com.aiglepub.pokemoncompose.data.datasource.remote

import android.location.Location

interface RegionDataSource {
    suspend fun findLastRegion(): String
    suspend fun Location.toRegion(): String
}

