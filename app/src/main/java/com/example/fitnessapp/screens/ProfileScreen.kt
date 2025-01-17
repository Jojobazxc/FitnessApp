package com.example.fitnessapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.fitnessapp.utils.OutputTextFieldForProfile
import com.example.fitnessapp.utils.SharedPreferencesManager
import com.example.fitnessapp.utils.TopFitnessBottomBarBar
import com.example.fitnessapp.utils.getListOfItems
import com.example.fitnessapp.viewmodels.RealmViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController: NavController, realmViewModel: RealmViewModel) {
    val me = SharedPreferencesManager.getObjectId()?.let { realmViewModel.getMe(it) }
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 80.dp, horizontal = 20.dp)
            ) {
                item {
                    OutputTextFieldForProfile(
                        title = "ВАШ ПОЛ:" ,
                        placeHolder = if (me?.userDetails?.gender == 0) "Женский" else "Мужской"
                    )
                }
                item {
                    OutputTextFieldForProfile(
                        title = "ВАШЕ ИМЯ:" ,
                        placeHolder = me?.userDetails?.name ?: ""
                    )
                }
                item {
                    OutputTextFieldForProfile(
                        title = "ВАШ ВЕС:" ,
                        placeHolder = "${me?.userDetails?.weight} кг"
                    )
                }
                item {
                    OutputTextFieldForProfile(
                        title = "ВАШ РОСТ:" ,
                        placeHolder = "${me?.userDetails?.height} см"
                    )
                }
                item {
                    OutputTextFieldForProfile(
                        title = "ВАШ EMAIL:" ,
                        placeHolder = me?.email.toString()
                    )
                }
            }

        }
    }
}