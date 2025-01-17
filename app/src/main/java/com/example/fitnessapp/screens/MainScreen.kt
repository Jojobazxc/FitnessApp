package com.example.fitnessapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.R
import com.example.fitnessapp.navigation.Screen
import com.example.fitnessapp.ui.theme.ButtonsColor
import com.example.fitnessapp.ui.theme.MainBGColor
import com.example.fitnessapp.ui.theme.mainFontFamily
import com.example.fitnessapp.utils.BottomNavItems
import com.example.fitnessapp.utils.TopFitnessBottomBarBar
import com.example.fitnessapp.utils.getListOfItems
import com.example.fitnessapp.viewmodels.RealmViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController) {



    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBGColor)
    ) {
        Scaffold(
            topBar = {
                Text(
                    text = "УПРАЖНЕНИЯ",
                    fontFamily = mainFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .padding(start = 20.dp, top = 20.dp)
                )
            },
            bottomBar = {
                TopFitnessBottomBarBar(
                    bottomNavItems = getListOfItems(),
                    navController = navController
                )
            }
        ) { innerPadding ->
            LazyColumn(

            ) { }

        }
    }
}



@Preview
@Composable
private fun MainScreenPrev() {
    MainScreen(rememberNavController())
}