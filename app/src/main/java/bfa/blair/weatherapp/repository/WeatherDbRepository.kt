package bfa.blair.weatherapp.repository

import bfa.blair.weatherapp.data.WeatherDao
import bfa.blair.weatherapp.model.room.Favorite
import bfa.blair.weatherapp.model.room.Unit
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDbRepository @Inject constructor(private val weatherDao: WeatherDao) {
    fun getFavorites() : Flow<List<Favorite>> = weatherDao.getFavorites()
    suspend fun insertFavorites(favorite: Favorite) = weatherDao.insertFavorite(favorite)
    suspend fun updateFavorites(favorite: Favorite) = weatherDao.updateFavorite(favorite)
    suspend fun deleteAllFavorites() = weatherDao.deleteAllFavorites()
    suspend fun deleteFavorite(favorite: Favorite) = weatherDao.deleteFavorite(favorite)
    suspend fun getFavoriteById(city : String) = weatherDao.getFavById(city)

    fun getUnits() : Flow<List<Unit>> = weatherDao.getUnits()
    suspend fun insertUnit(unit : Unit) = weatherDao.insertUnit(unit)
    suspend fun updateUnit(unit : Unit) = weatherDao.updateUnit(unit)
    suspend fun deleteAllUnits() = weatherDao.deleteAllUnits()
    suspend fun deleteUnit(unit : Unit) = weatherDao.deleteUnit(unit)
}