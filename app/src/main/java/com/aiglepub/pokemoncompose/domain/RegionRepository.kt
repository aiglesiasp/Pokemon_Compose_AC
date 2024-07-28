package com.aiglepub.pokemoncompose.domain

interface RegionRepository {
    suspend fun findLastRegion(): String
}