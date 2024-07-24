package com.aiglepub.pokemoncompose.framework

import android.annotation.SuppressLint
import android.location.Location
import com.aiglepub.pokemoncompose.data.datasource.remote.LocationDataSource
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class LocationDataSourceImpl(
    private val fusedLocationProviderClient: FusedLocationProviderClient
) : LocationDataSource {

    override suspend fun findLastLocation(): Location? = fusedLocationProviderClient.lastLocation()
}

@SuppressLint("MissingPermission")
private suspend fun FusedLocationProviderClient.lastLocation(): Location? {
    return suspendCancellableCoroutine { continuation ->
        lastLocation.addOnSuccessListener { location ->
            continuation.resume(location)
        }.addOnFailureListener {
            continuation.resume(null)
        }
    }
}