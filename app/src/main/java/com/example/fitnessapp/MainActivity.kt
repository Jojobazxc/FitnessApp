package com.example.fitnessapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.fitnessapp.navigation.Navigation
import com.example.fitnessapp.viewmodels.RealmViewModel

class MainActivity : ComponentActivity() {
    private val realmViewModel by viewModels<RealmViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
        }
    }
}
