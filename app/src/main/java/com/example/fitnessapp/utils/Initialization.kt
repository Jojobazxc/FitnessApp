package com.example.fitnessapp.utils

import android.app.Application
import com.example.fitnessapp.models.Category
import com.example.fitnessapp.models.Exercise
import com.example.fitnessapp.models.ExerciseDetails
import com.example.fitnessapp.models.User
import com.example.fitnessapp.models.UserDetails
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class Initialization : Application() {
    companion object {
        lateinit var realm: Realm
    }

    override fun onCreate() {
        super.onCreate()
        realm = Realm.open(
            configuration = RealmConfiguration.create(
                schema = setOf(
                    Category::class,
                    Exercise::class,
                    ExerciseDetails::class,
                    User::class,
                    UserDetails::class
                )
            )
        )
    }
}