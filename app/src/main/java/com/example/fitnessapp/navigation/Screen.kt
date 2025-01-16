package com.example.fitnessapp.navigation

sealed class Screen(val route: String) {
    data object LoginScreen: Screen("login_screen")
    data object RegisterScreen: Screen("register_screen")
}