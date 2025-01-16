package com.example.fitnessapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.screens.LoginScreen
import com.example.fitnessapp.screens.MainScreen
import com.example.fitnessapp.screens.RegisterScreen
import com.example.fitnessapp.utils.SharedPreferencesManager
import com.example.fitnessapp.viewmodels.AuthViewModel
import com.example.fitnessapp.viewmodels.RealmViewModel

@Composable
fun Navigation(realmViewModel: RealmViewModel, authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    var startDestination by remember { mutableStateOf(Screen.RegisterScreen.route) }
    val context = LocalContext.current
//    SharedPreferencesManager.init(context)
//    SharedPreferencesManager.deleteObjectId()
//    realmViewModel.deleteAllUsers()
    // Асинхронная проверка авторизации
    LaunchedEffect(Unit) {
        val isLoggedIn = authViewModel.isUserLoggedIn(context)
        startDestination = if (isLoggedIn == true) Screen.MainScreen.route else Screen.RegisterScreen.route
    }


    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(
            route = Screen.LoginScreen.route
        ) {
            LoginScreen(authViewModel = authViewModel, navController = navController)
        }
        composable(
            route = Screen.MainScreen.route
        ) {
            MainScreen()
        }
        composable(
            route = Screen.RegisterScreen.route
        ) {
            RegisterScreen(authViewModel = authViewModel, navController = navController)
        }
    }
}