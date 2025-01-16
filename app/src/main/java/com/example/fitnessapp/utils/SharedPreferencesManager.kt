package com.example.fitnessapp.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.fitnessapp.OBJECT_ID_KEY
import org.mongodb.kbson.ObjectId

object SharedPreferencesManager {
    private lateinit var sharedPrefs: SharedPreferences

    fun init(context: Context) {
        sharedPrefs = context.getSharedPreferences(OBJECT_ID_KEY, Context.MODE_PRIVATE)
    }

    fun putObjectId(objectId: ObjectId) {
        with(sharedPrefs.edit()) {
            putString(OBJECT_ID_KEY, objectId.toString())
            apply()
        }
    }

    fun getObjectId(): ObjectId {
        val objectId = sharedPrefs.getString(OBJECT_ID_KEY, null)
        return ObjectId(objectId!!)
    }
}