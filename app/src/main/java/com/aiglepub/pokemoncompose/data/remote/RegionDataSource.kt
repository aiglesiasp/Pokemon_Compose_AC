package com.aiglepub.pokemoncompose.data.remote

import android.location.Location

interface RegionDataSource {
    suspend fun findLastRegion(): String
}

