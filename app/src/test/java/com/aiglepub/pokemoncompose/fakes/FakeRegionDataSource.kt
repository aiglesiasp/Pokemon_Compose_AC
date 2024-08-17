package com.aiglepub.pokemoncompose.fakes

import com.aiglepub.pokemoncompose.data.DEFAULT_REGION
import com.aiglepub.pokemoncompose.data.remote.RegionDataSource

class FakeRegionDataSource: RegionDataSource {
    private var region: String = DEFAULT_REGION

    override suspend fun findLastRegion(): String = region
}