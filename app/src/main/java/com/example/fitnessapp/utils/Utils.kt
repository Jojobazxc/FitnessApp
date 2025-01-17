package com.example.fitnessapp.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fitnessapp.ui.theme.ButtonsColor
import com.example.fitnessapp.ui.theme.mainFontFamily

@Composable
fun FitnessAppTextFieldForText(
    value: MutableState<String>,
    placeHolder: String
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        value = value.value,
        onValueChange = { value.value = it },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = ButtonsColor,
            unfocusedTextColor = ButtonsColor,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            cursorColor = ButtonsColor,
            unfocusedBorderColor = Color.White,
            focusedBorderColor = Color.White
        ),
        shape = RoundedCornerShape(15.dp),
        placeholder = {
            Text(
                text = placeHolder,
                fontFamily = mainFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = ButtonsColor
            )
        }
    )
}

@Composable
fun FitnessAppTextFieldForNumbers(
    value: MutableState<String>,
    placeHolder: String
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        value = value.value,
        onValueChange = { value.value = it.filter { it.isDigit() } },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = ButtonsColor,
            unfocusedTextColor = ButtonsColor,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            cursorColor = ButtonsColor,
            unfocusedBorderColor = Color.White,
            focusedBorderColor = Color.White
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        shape = RoundedCornerShape(15.dp),
        placeholder = {
            Text(
                text = placeHolder,
                fontFamily = mainFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = ButtonsColor
            )
        }
    )
}

@Composable
fun FitnessAppTextFieldForPassword(
    value: MutableState<String>,
    placeHolder: String
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        value = value.value,
        onValueChange = { value.value = it.filter { it.isDigit() } },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = ButtonsColor,
            unfocusedTextColor = ButtonsColor,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            cursorColor = ButtonsColor,
            unfocusedBorderColor = Color.White,
            focusedBorderColor = Color.White
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        shape = RoundedCornerShape(15.dp),
        placeholder = {
            Text(
                text = placeHolder,
                fontFamily = mainFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = ButtonsColor
            )
        }
    )
}

@Composable
fun TopFitnessBottomBarBar(
    bottomNavItems: List<BottomNavItems>,
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val selectedItemIndex = remember { mutableStateOf(0) }

    LaunchedEffect(currentRoute) {
        bottomNavItems.forEachIndexed { index, bottomNavItems ->
            if (bottomNavItems.route == currentRoute) {
                selectedItemIndex.value = index
            }
        }
    }
    NavigationBar(containerColor = Color.White) {
        bottomNavItems.forEachIndexed { index, bottomNavItems ->
            NavigationBarItem(
                selected = selectedItemIndex.value == index,
                onClick = {
                    selectedItemIndex.value = index
                    navController.navigate(bottomNavItems.route)
                },
                label = {
                    Text(
                        modifier = Modifier,
                        text = bottomNavItems.title,
                        fontSize = 10.sp,
                        fontFamily = mainFontFamily,
                        fontWeight = FontWeight.Normal,
                        letterSpacing = 0.4.sp
                    )
                },
                icon = {
                    Icon(
                        imageVector = if (index == selectedItemIndex.value) bottomNavItems.selectedIcon
                        else bottomNavItems.unselectedIcon,
                        contentDescription = "null",
                        modifier = Modifier
                            .size(25.dp)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = ButtonsColor,
                    unselectedIconColor = Color.Black,
                    selectedTextColor = ButtonsColor,
                    unselectedTextColor = Color.Black,
                    indicatorColor = Color.White
                )
            )

        }
    }
}