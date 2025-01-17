package com.example.fitnessapp.navigation

sealed class Screen(val route: String) {
    data object LoginScreen: Screen("login_screen")
    data object RegisterScreen: Screen("register_screen")
    data object ProfileScreen: Screen("Profile_screen")
    data object StartScreen: Screen("start_screen")
    data object CategoryScreen: Screen("category_screen")
    data object ExercisesScreen: Screen("exercises_screen")
    data object ExerciseDetailsScreen: Screen("exercise_details_screen")
}