package bfa.blair.weatherapp.model.api

data class WeatherObject(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)