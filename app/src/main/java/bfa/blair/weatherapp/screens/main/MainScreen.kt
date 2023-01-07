package bfa.blair.weatherapp.screens.main

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import bfa.blair.weatherapp.R
import bfa.blair.weatherapp.data.DataOrException
import bfa.blair.weatherapp.model.Weather
import bfa.blair.weatherapp.model.WeatherItem
import bfa.blair.weatherapp.navigation.WeatherScreens
import bfa.blair.weatherapp.utils.formatDate
import bfa.blair.weatherapp.utils.formatDateTime
import bfa.blair.weatherapp.utils.formatDecimals
import bfa.blair.weatherapp.widgets.*
import coil.compose.rememberImagePainter
import kotlin.text.Typography

@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel) {

    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)) {
        value = mainViewModel.getWeatherData(city = "nairobi")
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
            onAddActionClicked = {
                navController.navigate(WeatherScreens.SearchScreen.name)
            },
            elevation = 1.dp) {
            Log.d("Clicked", "Button Clicked")
        }
    }) {
        MainContent(data = weather)
    }

}

@Composable
fun MainContent(data : Weather) {

    val weatherItem = data.list[0]
    val imageUrl = "https://openweathermap.org/img/wn/${weatherItem.weather[0].icon}.png"

    Column(Modifier
        .padding(4.dp)
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Text(text = formatDate(weatherItem.dt),
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSecondary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(6.dp))
        
        Surface(modifier = Modifier
            .padding(4.dp)
            .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)) {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                WeatherStateImg(imageUrl = imageUrl)
                Text(text = formatDecimals(weatherItem.temp.day) + "°", style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.ExtraBold)
                Text(text = weatherItem.weather[0].main, fontStyle = FontStyle.Italic)
            }
        }

        HumidityWindPressureRow(weather = weatherItem)
        Divider()
        SunsetSunriseRow(weather = weatherItem)
        Text(text = "This Week",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.subtitle1)
        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            color = Color(0xFFEEF1EF),
            shape = RoundedCornerShape(size = 14.dp),
            ) {
            LazyColumn(modifier = Modifier.padding(2.dp),
                contentPadding = PaddingValues(1.dp)) {
                items(items = data.list) { item : WeatherItem ->
                    WeatherDetailRow(weather = item)
                }
            }
        }
    }
}

