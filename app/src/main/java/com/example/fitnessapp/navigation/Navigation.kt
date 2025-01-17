package com.example.fitnessapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fitnessapp.screens.LoginScreen
import com.example.fitnessapp.screens.CategoryScreen
import com.example.fitnessapp.screens.ExerciseDetailsScreen
import com.example.fitnessapp.screens.ExercisesScreen
import com.example.fitnessapp.screens.ProfileScreen
import com.example.fitnessapp.screens.RegisterScreen
import com.example.fitnessapp.screens.StartScreen
import com.example.fitnessapp.viewmodels.AuthViewModel
import com.example.fitnessapp.viewmodels.RealmViewModel

@Composable
fun Navigation(realmViewModel: RealmViewModel, authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    var startDestination by remember { mutableStateOf(Screen.StartScreen.route) }
    val context = LocalContext.current
//    SharedPreferencesManager.init(context)
//    SharedPreferencesManager.deleteObjectId()
//    realmViewModel.deleteAllUsers()


    LaunchedEffect(Unit) {
        val isLoggedIn = authViewModel.isUserLoggedIn(context)
        startDestination = if (isLoggedIn == true) Screen.CategoryScreen.route else Screen.RegisterScreen.route
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
            route = Screen.CategoryScreen.route
        ) {
            CategoryScreen(realmViewModel = realmViewModel, navController = navController)
        }
        composable(
            route = Screen.RegisterScreen.route
        ) {
            RegisterScreen(authViewModel = authViewModel, navController = navController)
        }
        composable(
            route = Screen.StartScreen.route
        ) {
            StartScreen()
        }
        composable(
            route = Screen.ProfileScreen.route
        ) {
            ProfileScreen(navController = navController, realmViewModel = realmViewModel)
        }
        composable(
            route = Screen.ExercisesScreen.route + "/{name}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val name = navBackStackEntry.arguments?.getString("name") ?: ""
            ExercisesScreen(navController = navController, name = name, realmViewModel = realmViewModel )
        }
        composable(
            route = Screen.ExerciseDetailsScreen.route + "/{name}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            val name = navBackStackEntry.arguments?.getString("name") ?: ""
            ExerciseDetailsScreen(navController = navController, name = name, realmViewModel = realmViewModel)

        }
    }
}