package com.example.fitnessapp.utils

import android.app.Application
import android.util.Log
import androidx.compose.ui.platform.LocalContext
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
//        deleteRealmFile()
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
    private fun deleteRealmFile() {
        val config = RealmConfiguration.Builder(
            schema = setOf(
                Category::class,
                Exercise::class,
                ExerciseDetails::class,
                User::class,
                UserDetails::class
            )
        ).deleteRealmIfMigrationNeeded().build()

        Realm.deleteRealm(config)
        Log.d("Realm", "Realm file deleted")
    }

}