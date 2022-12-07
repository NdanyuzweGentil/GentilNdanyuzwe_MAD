package com.example.lab4_bottomnavigation_app.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lab4_bottomnavigation_app.Screens.HomeScreen
import com.example.lab4_bottomnavigation_app.Screens.ProfileScreen
import com.example.lab4_bottomnavigation_app.Screens.SettingsScreen

@Composable
fun BottomNavStructure(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen()
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen()
        }
        composable(route = BottomBarScreen.Settings.route) {
            SettingsScreen()
        }
    }
}