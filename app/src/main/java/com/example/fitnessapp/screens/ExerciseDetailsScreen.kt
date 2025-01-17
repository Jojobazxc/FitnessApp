package com.example.fitnessapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.R
import com.example.fitnessapp.ui.theme.ButtonsColor
import com.example.fitnessapp.ui.theme.FirstTextColor
import com.example.fitnessapp.ui.theme.mainFontFamily
import com.example.fitnessapp.utils.GetExerciseImage
import com.example.fitnessapp.viewmodels.RealmViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseDetailsScreen(
    navController: NavController,
    name: String,
    realmViewModel: RealmViewModel,
) {
    val details = realmViewModel.getExerciseDetailByName(name)
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                Column {
                    TopAppBar(
                        title = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Image(
                                    imageVector = ImageVector.vectorResource(R.drawable.ic_back),
                                    contentDescription = "back",
                                    colorFilter = ColorFilter.tint(ButtonsColor),
                                    modifier = Modifier
                                        .clickable {
                                            navController.navigateUp()
                                        }
                                        .size(25.dp)
                                )
                            }
                        },
                    )
                    TopAppBar(
                        title = {
                            Text(
                                text = name,
                                fontFamily = mainFontFamily,
                                fontWeight = FontWeight.Medium,
                                color = FirstTextColor,
                                modifier = Modifier
                                    .offset(y = (-22).dp)
                            )
                        }
                    )

                }
            }
        ) {innerPadding ->
            LazyColumn (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 100.dp,
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 20.dp
                    ),
            ) {
                item {
                    GetExerciseImage(
                        imagePath = details.videoPath
                    )
                }
                item {
                    Text(
                        text = "Описание",
                        fontFamily = mainFontFamily,
                        fontWeight = FontWeight.Medium,
                        color = FirstTextColor,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .padding(top = 20.dp)
                    )
                }
                item {
                    Text(
                        text = details.description,
                        fontFamily = mainFontFamily,
                        fontWeight = FontWeight.Normal,
                        color = FirstTextColor,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(top = 20.dp)
                    )
                }
                item {
                    Text(
                        text = "Важно!",
                        fontFamily = mainFontFamily,
                        fontWeight = FontWeight.Medium,
                        color = FirstTextColor,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .padding(top = 20.dp)
                    )
                }
                item {
                    Text(
                        text = details.importantText,
                        fontFamily = mainFontFamily,
                        fontWeight = FontWeight.Normal,
                        color = FirstTextColor,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(top = 20.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ExerciseDetailsScreenPrev() {
    val realmViewModel = RealmViewModel()
    ExerciseDetailsScreen(rememberNavController(), "Приседания", realmViewModel)
}

