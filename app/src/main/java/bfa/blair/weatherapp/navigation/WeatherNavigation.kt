package bfa.blair.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import bfa.blair.weatherapp.model.Weather
import bfa.blair.weatherapp.screens.WeatherSplashScreen
import bfa.blair.weatherapp.screens.about.AboutScreen
import bfa.blair.weatherapp.screens.favorite.FavoriteScreen
import bfa.blair.weatherapp.screens.main.MainScreen
import bfa.blair.weatherapp.screens.main.MainViewModel
import bfa.blair.weatherapp.screens.search.SearchScreen
import bfa.blair.weatherapp.screens.settings.SettingsScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = WeatherScreens.SplashScreen.name) {
        composable(WeatherScreens.SplashScreen.name) {
            WeatherSplashScreen(navController = navController)
        }

        val route = WeatherScreens.MainScreen.name
        composable("$route/{city}",
            arguments = listOf(
                navArgument(name = "city") {
                    type = NavType.StringType
                })) { navBack ->
                    navBack.arguments?.getString("city").let{ city ->
                        val mainViewModel = hiltViewModel<MainViewModel>()
                        MainScreen(navController = navController, mainViewModel, city = city)
                    }

        }
        composable(WeatherScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }
        composable(WeatherScreens.AboutScreen.name) {
            AboutScreen(navController = navController)
        }
        composable(WeatherScreens.FavoritesScreen.name) {
            FavoriteScreen(navController = navController)
        }
        composable(WeatherScreens.SettingsScreen.name) {
            SettingsScreen(navController = navController)
        }
    }
}