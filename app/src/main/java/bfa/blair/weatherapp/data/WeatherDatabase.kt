package bfa.blair.weatherapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import bfa.blair.weatherapp.model.room.Favorite
import bfa.blair.weatherapp.model.room.Unit

@Database(entities = [Favorite::class, Unit::class], version = 2, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao() : WeatherDao
}