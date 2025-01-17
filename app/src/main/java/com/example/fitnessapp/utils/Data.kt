package com.example.fitnessapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.fitnessapp.R
import com.example.fitnessapp.navigation.Screen

data class BottomNavItems(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String,
)
@Composable
fun getListOfItems(): List<BottomNavItems> {
    return listOf(
        BottomNavItems(
            title = "Упражнения",
            selectedIcon = ImageVector.vectorResource(R.drawable.ic_exercise),
            unselectedIcon = ImageVector.vectorResource(R.drawable.ic_exercise),
            route = Screen.CategoryScreen.route
        ),
        BottomNavItems(
            title = "Профиль",
            selectedIcon = ImageVector.vectorResource(R.drawable.ic_profile),
            unselectedIcon = ImageVector.vectorResource(R.drawable.ic_profile),
            route = Screen.ProfileScreen.route
        )
    )

}
