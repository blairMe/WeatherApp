package bfa.blair.weatherapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import bfa.blair.weatherapp.model.room.Favorite

@Database(entities = [Favorite::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao() : WeatherDao
}