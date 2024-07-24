package com.aiglepub.pokemoncompose.data.remote

import android.location.Location

interface LocationDataSource {
    suspend fun findLastLocation(): Location?
}

