package com.example.fitnessapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessapp.ui.theme.ButtonsColor
import com.example.fitnessapp.ui.theme.FirstTextColor
import com.example.fitnessapp.ui.theme.MainBGColor
import com.example.fitnessapp.ui.theme.mainFontFamily

@Composable
fun StartScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MainBGColor),
    ) {
        Text(
            text = "FitnessApp",
            modifier = Modifier,
            fontSize = 26.sp,
            color = FirstTextColor,
            fontFamily = mainFontFamily,
            fontWeight = FontWeight.Medium
        )
        CircularProgressIndicator(
            modifier = Modifier
                .padding(50.dp),
            color = ButtonsColor
        )
    }
}

@Preview
@Composable
private fun SSPrev() {
    StartScreen()
}