package bfa.blair.weatherapp.screens.main

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import bfa.blair.weatherapp.data.DataOrException
import bfa.blair.weatherapp.model.Weather
import bfa.blair.weatherapp.widgets.WeatherAppBar

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel) {

    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)) {
        value = mainViewModel.getWeatherData(city = "Seattle")
    }.value

    if(weatherData.loading == true) {
        CircularProgressIndicator()
    } else if (weatherData.data != null){
        // Text(text = "MainScreen ${weatherData.value.data}")
        MainScaffold(weather = weatherData.data!!, navController)
    }
}

@Composable
fun MainScaffold(weather: Weather, navController: NavController) {
    Scaffold(topBar = {
        WeatherAppBar(title = weather.city.name + ", ${weather.city.country}",
            navController = navController,
            elevation = 5.dp) {
            Log.d("Clicked", "Button Clicked")
        }
    }) {
        MainContent(data = weather)
    }

}

@Composable
fun MainContent(data : Weather) {
    Column(Modifier
        .padding(4.dp)
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Text(text = "Nov 19",
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSecondary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(6.dp))
        
        Surface() {
            
        }
        
    }
}