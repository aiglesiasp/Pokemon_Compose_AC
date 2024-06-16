package com.aiglepub.pokemoncompose.data

import com.aiglepub.pokemoncompose.data.datasource.RegionDataSource

class RegionRepository(private val regionDataSource: RegionDataSource) {
    suspend fun findLastRegion(): String = regionDataSource.findLastRegion()
}