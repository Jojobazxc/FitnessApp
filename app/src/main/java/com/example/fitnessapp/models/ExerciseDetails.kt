package com.example.fitnessapp.models

import io.realm.kotlin.types.EmbeddedRealmObject

class ExerciseDetails: EmbeddedRealmObject {
    var videoPath: String = ""
    var description: String = ""
    var importantText: String = ""
}