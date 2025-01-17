package com.example.fitnessapp.navigation

sealed class Screen(val route: String) {
    data object LoginScreen: Screen("login_screen")
    data object RegisterScreen: Screen("register_screen")
    data object MainScreen: Screen("main_screen")
    data object StartScreen: Screen("start_screen")
    data object ProfileScreen: Screen("profile_screen")
}