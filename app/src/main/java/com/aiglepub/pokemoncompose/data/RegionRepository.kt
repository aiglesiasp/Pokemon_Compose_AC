package com.aiglepub.pokemoncompose.data

import com.aiglepub.pokemoncompose.data.datasource.remote.RegionDataSource

const val DEFAULT_REGION = "US"

class RegionRepository(private val regionDataSource: RegionDataSource) {
    suspend fun findLastRegion(): String = regionDataSource.findLastRegion()
}