package com.example.fitnessapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fitnessapp.R
import com.example.fitnessapp.navigation.Screen
import com.example.fitnessapp.ui.theme.ButtonsColor
import com.example.fitnessapp.ui.theme.FirstTextColor
import com.example.fitnessapp.ui.theme.mainFontFamily
import com.example.fitnessapp.utils.ExerciseItem
import com.example.fitnessapp.viewmodels.RealmViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ExercisesScreen(navController: NavController, realmViewModel: RealmViewModel, name: String) {
    val listOfExercises = realmViewModel.getListOfExercisesByName(name = name)
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Row (
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Image(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_back),
                                contentDescription = "back",
                                colorFilter = ColorFilter.tint(ButtonsColor),
                                modifier = Modifier.clickable {
                                    navController.navigateUp()
                                }.size(25.dp)
                            )
                            Text(
                                text = name,
                                fontFamily = mainFontFamily,
                                fontWeight = FontWeight.Normal,
                                color = FirstTextColor,
                            )
                            Image(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_profile),
                                contentDescription = "back",
                                colorFilter = ColorFilter.tint(ButtonsColor),
                                modifier = Modifier.clickable {
                                    navController.navigate(Screen.ProfileScreen.route)
                                }.padding(end = 20.dp)
                            )
                        }
                    },
                    navigationIcon = {

                    }
                )
            }
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 80.dp, horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(listOfExercises) { exercise ->
                    ExerciseItem(
                        name = exercise.name,
                        navController = navController,
                        imagePath = exercise.avatarPath
                    )
                }
            }
        }
    }
}


