package bfa.blair.weatherapp.repository

import android.util.Log
import bfa.blair.weatherapp.data.DataOrException
import bfa.blair.weatherapp.model.api.Weather
import bfa.blair.weatherapp.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api : WeatherApi) {
    suspend fun getWeather(cityQuery : String) :
            DataOrException<Weather, Boolean, Exception> {
        val response = try {
            api.getWeather(query = cityQuery)
        } catch (e : Exception) {
            return DataOrException(e = e)
        }
        Log.d("INSIDE", "getWeather: $response")
        return DataOrException(data = response)
    }
}