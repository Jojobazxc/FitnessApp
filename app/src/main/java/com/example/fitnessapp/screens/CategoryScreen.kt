package com.example.fitnessapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.ui.theme.MainBGColor
import com.example.fitnessapp.ui.theme.mainFontFamily
import com.example.fitnessapp.utils.CategoryItem
import com.example.fitnessapp.utils.TopFitnessBottomBarBar
import com.example.fitnessapp.utils.getListOfItems
import com.example.fitnessapp.viewmodels.RealmViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CategoryScreen(navController: NavController, realmViewModel: RealmViewModel) {

    val listOfCategories = realmViewModel.getCategories()

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
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .padding(vertical = 60.dp, horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(listOfCategories) { category ->
                    CategoryItem(
                        name = category.name,
                        imagePath = category.avatarPath,
                        navController = navController
                    )
                }
            }

        }
    }
}



@Preview
@Composable
private fun MainScreenPrev() {

}