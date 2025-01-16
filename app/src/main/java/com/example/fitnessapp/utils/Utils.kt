package com.example.fitnessapp.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

