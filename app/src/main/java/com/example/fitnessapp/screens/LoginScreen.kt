package com.example.fitnessapp.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fitnessapp.navigation.Screen
import com.example.fitnessapp.utils.AuthState
import com.example.fitnessapp.viewmodels.AuthViewModel

@Composable
fun LoginScreen(authViewModel: AuthViewModel, navController: NavController) {
    val context = LocalContext.current
    val authState = authViewModel.authState.collectAsState()
//    SharedPreferencesManager.init(context)
//    SharedPreferencesManager.deleteObjectId()
//    Log.d("SP", "Object delete")
    LaunchedEffect(authState.value) {
        if (authState.value is AuthState.Success) {
            navController.navigate(Screen.CategoryScreen.route) {
                popUpTo(Screen.LoginScreen.route) { inclusive = true }
            }
        } else if (authState.value is AuthState.Error) {
            Log.e(
                "ErrorAuth",
                "Ошибка авторизации: ${(authState.value as AuthState.Error).message}"
            )
        }
    }
    LoginCard(login = { email, password, context ->
        authViewModel.login(email, password, context)
    }, context, navController)

}

@Composable
fun LoginCard(login: (String, String, Context) -> (Unit), context: Context, navController: NavController) {
    val emailState = remember {
        mutableStateOf("")
    }
    val passwordState = remember {
        mutableStateOf("")
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(
                        start = 20.dp,
                        top = 20.dp,
                        end = 20.dp
                    ),
                value = emailState.value,
                onValueChange = { emailState.value = it },
                placeholder = {
                    Text(text = "Логин")
                }
            )
            OutlinedTextField(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(20.dp),
                value = passwordState.value,
                onValueChange = { passwordState.value = it },
                placeholder = {
                    Text(text = "Пароль")
                }
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 20.dp
                    ),
                onClick = {
                    login(emailState.value, passwordState.value, context)
                },
                shape = RoundedCornerShape(8.dp),
                content = {
                    Text("Войти")
                }
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 20.dp
                    ),
                onClick = {
                    navController.navigate(Screen.RegisterScreen.route)
                },
                shape = RoundedCornerShape(8.dp),
                content = {
                    Text("Зарегистрироваться")
                }
            )
        }
    }
}

/*
@Preview
@Composable
private fun LoginPrev() {
    LoginCard()
}*/
