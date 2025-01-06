package com.example.fitnessapp.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Exercise: RealmObject {
    @PrimaryKey var _id: ObjectId = ObjectId()
    val name: String = ""
    val avatarPath = ""
    val exerciseDetails: ExerciseDetails? = null
}