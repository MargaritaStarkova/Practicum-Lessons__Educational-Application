package com.example.apiwheather_rxjava

import com.google.gson.annotations.SerializedName

class ForecaAuthResponse(@SerializedName("access_token") val token: String)

class LocationsResponse(val locations: ArrayList<ForecastLocation>)

class ForecastResponse(val current: CurrentWeather)
