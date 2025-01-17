package com.example.fitnessapp.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fitnessapp.R
import com.example.fitnessapp.navigation.Screen
import com.example.fitnessapp.ui.theme.ButtonsColor
import com.example.fitnessapp.ui.theme.CardGradientEndColor
import com.example.fitnessapp.ui.theme.CardGradientStartColor
import com.example.fitnessapp.ui.theme.FirstTextColor
import com.example.fitnessapp.ui.theme.mainFontFamily
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

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

@Composable
fun CategoryItem(
    name: String,
    imagePath: String,
    navController: NavController
) {
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current

    LaunchedEffect(imagePath) {
        bitmap = withContext(Dispatchers.IO) {
            loadImageFromAssets(context = context, imagePath = imagePath)
        }
    }

    val gradient = Brush.verticalGradient(
        colors = listOf(CardGradientStartColor, CardGradientEndColor)
    )
    Card (
        modifier = Modifier
            ,
        shape = RoundedCornerShape(20.dp),
        onClick = {
            navController.navigate(Screen.ExercisesScreen.route + "/$name")
        }
    ) {
        Box(
            modifier = Modifier
                .background(gradient)
        ) {
            bitmap?.asImageBitmap()?.let {
                Image(
                    bitmap = it,
                    contentDescription = "",
                    alignment = Alignment.TopEnd,
                    modifier = Modifier
                )
            }
            Text(
                text = name.uppercase(),
                modifier = Modifier
                    .padding(
                        top = 40.dp,
                        start = 10.dp
                    ),
                fontFamily = mainFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp
            )
        }

    }
}

@Composable
fun ExerciseItem(
    name: String,
    navController: NavController,
    imagePath: String
) {

    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current

    LaunchedEffect(imagePath) {
        bitmap = withContext(Dispatchers.IO) {
            loadImageFromAssets(context = context, imagePath = imagePath)
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .clickable {
                navController.navigate(Screen.ExerciseDetailsScreen.route + "/$name")
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                bitmap?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = "",
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
                Text(
                    text = name,
                    fontFamily = mainFontFamily,
                    fontWeight = FontWeight.Normal,
                    color = FirstTextColor,
                    modifier = Modifier
                        .padding(start = 12.dp)
                )
            }
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_next),
                contentDescription = "",
                modifier = Modifier
                    .padding(end = 20.dp)
                    .size(15.dp)
                    .clickable {
                        navController.navigate(Screen.ExerciseDetailsScreen.route + "/$name")
                    },
                colorFilter = ColorFilter.tint(ButtonsColor)

            )
        }
    }
}

@Composable
fun GetExerciseImage(imagePath: String) {

    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current

    LaunchedEffect(imagePath){
        bitmap = loadImageFromAssets(
            context = context,
            imagePath = imagePath
        )
    }

    Box (
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        bitmap?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
        }
    }
}

private fun loadImageFromAssets(context: Context, imagePath: String): Bitmap? {
    return try {
        val assetManager = context.assets
        val inputStream = assetManager.open(imagePath)
        BitmapFactory.decodeStream(inputStream)
    } catch (e: IOException) {
        null
    }
}


@Preview
@Composable
private fun UtilsPrev() {

}