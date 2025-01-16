package com.example.fitnessapp.screens

import android.content.Context
import android.text.Layout
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fitnessapp.R
import com.example.fitnessapp.navigation.Screen
import com.example.fitnessapp.ui.theme.ButtonsColor
import com.example.fitnessapp.ui.theme.FirstTextColor
import com.example.fitnessapp.ui.theme.MainBGColor
import com.example.fitnessapp.ui.theme.mainFontFamily
import com.example.fitnessapp.utils.FitnessAppTextFieldForNumbers
import com.example.fitnessapp.utils.FitnessAppTextFieldForPassword
import com.example.fitnessapp.utils.FitnessAppTextFieldForText
import com.example.fitnessapp.utils.GENDER_FEMALE
import com.example.fitnessapp.utils.GENDER_MALE
import com.example.fitnessapp.viewmodels.AuthViewModel
import java.util.Locale

@Composable
fun RegisterScreen(navController: NavController, authViewModel: AuthViewModel) {
    val authState = authViewModel.authState.collectAsState()
    RegisterHorizontalPager(authViewModel, navController, LocalContext.current)
}

@Composable
fun RegisterHorizontalPager(
    authViewModel: AuthViewModel,
    navController: NavController,
    context: Context
) {
    val pagerCount = 6
    val pagerState = rememberPagerState {
        pagerCount
    }
    val genders = listOf("Мужской", "Женский")
    var selectedGender = remember { mutableStateOf<String?>(null) }
    var selectedItem by remember {
        mutableIntStateOf(0)
    }
    val age = remember {
        mutableStateOf("")
    }
    val name = remember {
        mutableStateOf("")
    }
    val heightState = remember {
        mutableStateOf("")
    }
    val weightState = remember {
        mutableStateOf("")
    }
    val emailState = remember {
        mutableStateOf("")
    }
    val passwordState = remember {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = selectedItem) {
        pagerState.animateScrollToPage(selectedItem)
    }
    LaunchedEffect(key1 = pagerState.currentPage) {
        selectedItem = pagerState.currentPage
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBGColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.65f)
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_back),
                contentDescription = "back",
                modifier = Modifier
                    .padding(
                        horizontal = 20.dp,
                        vertical = 40.dp
                    )
                    .size(25.dp)
                    .clickable {
                        if (selectedItem > 0) {
                            selectedItem--
                        }
                    },
                colorFilter = ColorFilter.tint(ButtonsColor)
            )
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) { index ->
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Log.d("SelectedItem", selectedItem.toString())
                when (index) {
                    0 -> {
                        RegisterHeader("Ваш пол:")
                        genders.forEach { it ->
                            GenderRadioButton(
                                text = it,
                                gender = it,
                                selectedGender = selectedGender
                            )
                        }
                    }

                    1 -> {
                        RegisterHeader("Сколько Вам лет?")
                        FitnessAppTextFieldForNumbers(
                            value = age,
                            placeHolder = "Введите ваш возраст"
                        )
                    }

                    2 -> {
                        RegisterHeader("Как Вас зовут?")
                        FitnessAppTextFieldForText(
                            value = name,
                            placeHolder = "Введите Ваше имя"
                        )
                    }

                    3 -> {
                        RegisterHeader("Какой у Вас рост?")
                        FitnessAppTextFieldForNumbers(
                            value = heightState,
                            placeHolder = "Введите Ваш рост"
                        )
                    }

                    4 -> {
                        RegisterHeader("Какой у Вас вес?")
                        FitnessAppTextFieldForNumbers(
                            value = weightState,
                            placeHolder = "Введите Ваш вес"
                        )
                    }

                    5 -> {
                        RegisterHeader("Ваш Email?")
                        FitnessAppTextFieldForText(
                            value = emailState,
                            placeHolder = "Email"
                        )
                    }

                    6 -> {
                        RegisterHeader("Придумайте пароль")
                        FitnessAppTextFieldForPassword(
                            value = weightState,
                            placeHolder = "Пароль"
                        )
                    }
                }

            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 40.dp
                ),
            onClick = {
                if (selectedItem < pagerCount) {
                    selectedItem ++
                }
                if (selectedItem == 6) {
                    authViewModel.register(
                        age = age.value.toInt(),
                        email = emailState.value,
                        password = passwordState.value,
                        name = name.value,
                        gender = if (selectedGender.value == genders[0]) GENDER_MALE else GENDER_FEMALE,
                        weight = weightState.value.toInt(),
                        height = heightState.value.toInt(),
                        context = context
                    )
                    navController.navigate(Screen.MainScreen.route)
                }
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ButtonsColor
            ),
            content = {
                Text(
                    text = if (selectedItem == 6) "Зарегистрироваться" else "Далее",
                    fontFamily = mainFontFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        )
    }
}

@Composable
fun GenderRadioButton(
    text: String,
    gender: String,
    selectedGender: MutableState<String?>
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_gender_radio_button),
                    contentDescription = "null",
                )
                Text(
                    text = text.uppercase(Locale.getDefault()),
                    modifier = Modifier.padding(start = 20.dp),
                    fontFamily = mainFontFamily,
                    fontSize = 16.sp,
                )
            }
            RadioButton(
                selected = gender == selectedGender.value,
                onClick = { selectedGender.value = gender },
                colors = RadioButtonDefaults.colors(
                    selectedColor = ButtonsColor,
                    unselectedColor = ButtonsColor
                )
            )
        }
    }
}

@Composable
fun RegisterHeader(text: String) {
    Text(
        text = text,
        color = FirstTextColor,
        fontFamily = mainFontFamily,
        fontSize = 22.sp,
        fontWeight = FontWeight.Medium,
        modifier = Modifier
            .padding(bottom = 40.dp),
    )
}




@Preview
@Composable
private fun RegisterPrev() {

}