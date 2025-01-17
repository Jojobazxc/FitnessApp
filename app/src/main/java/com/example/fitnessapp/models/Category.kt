package com.example.fitnessapp.models

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Category: RealmObject {
    @PrimaryKey
    var name: String = ""
    var avatarPath = ""
    var exercises: RealmList<Exercise>? = realmListOf()
}