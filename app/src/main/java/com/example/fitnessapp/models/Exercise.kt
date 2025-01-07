package com.example.fitnessapp.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Exercise: RealmObject {
    @PrimaryKey var _id: ObjectId = ObjectId()
    var name: String = ""
    var avatarPath = ""
    var exerciseDetails: ExerciseDetails? = null
}