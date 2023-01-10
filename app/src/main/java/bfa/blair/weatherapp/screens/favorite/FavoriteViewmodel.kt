package bfa.blair.weatherapp.screens.favorite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bfa.blair.weatherapp.model.room.Favorite
import bfa.blair.weatherapp.repository.WeatherDbRepository
import bfa.blair.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteViewmodel @Inject constructor(private val repository: WeatherDbRepository)
    : ViewModel() {
        private val _favlist = MutableStateFlow<List<Favorite>>(emptyList())
        val favlist = _favlist.asStateFlow()

        init {
            viewModelScope.launch(Dispatchers.IO) {
                repository.getFavorites().distinctUntilChanged()
                    .collect{ listOfFavs ->
                        if (listOfFavs.isNullOrEmpty()) {
                            Log.d("List of Favs", ": Empty")
                        } else {
                            _favlist.value = listOfFavs
                            Log.d("List of Favs", ": ${_favlist.value}")
                        }
                    }
            }
        }

    fun insertFavorite(favorite: Favorite)
        = viewModelScope.launch { repository.insertFavorites(favorite) }
    fun updateFavorite(favorite: Favorite)
        = viewModelScope.launch { repository.updateFavorites(favorite) }
    fun deleteFavorite(favorite: Favorite)
        = viewModelScope.launch { repository.deleteFavorite(favorite) }
}