package com.example.fitnessapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fitnessapp.ui.theme.MainBGColor
import com.example.fitnessapp.ui.theme.mainFontFamily
import com.example.fitnessapp.utils.TopFitnessBottomBarBar
import com.example.fitnessapp.utils.getListOfItems

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController: NavController) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBGColor)
    ) {
        Scaffold(
            topBar = {
                Text(
                    text = "ПРОФИЛЬ",
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