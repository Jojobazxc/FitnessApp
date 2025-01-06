package com.example.fitnessapp.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class User: RealmObject {
    @PrimaryKey var _id: ObjectId = ObjectId()
    var email: String = ""
    var password: String = ""
    var userDetails: UserDetails? = null
}