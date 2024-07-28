package com.aiglepub.pokemoncompose.data

import com.aiglepub.pokemoncompose.data.remote.RegionDataSource
import com.aiglepub.pokemoncompose.domain.RegionRepository
import javax.inject.Inject

const val DEFAULT_REGION = "US"

class RegionRepositoryImpl @Inject constructor(private val regionDataSource: RegionDataSource) : RegionRepository {
    override suspend fun findLastRegion(): String = regionDataSource.findLastRegion()
}