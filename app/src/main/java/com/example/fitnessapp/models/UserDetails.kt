package com.example.fitnessapp.models

import io.realm.kotlin.types.EmbeddedRealmObject

class UserDetails: EmbeddedRealmObject {
    var name: String = ""
    var gender: String = ""
    var height: Int? = null
    var weight: Int? = null
    var age: Int? = null
}