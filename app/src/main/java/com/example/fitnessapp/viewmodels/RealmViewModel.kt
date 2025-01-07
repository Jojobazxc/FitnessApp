package com.example.fitnessapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.models.Category
import com.example.fitnessapp.models.Exercise
import com.example.fitnessapp.models.ExerciseDetails
import com.example.fitnessapp.models.User
import com.example.fitnessapp.models.UserDetails
import com.example.fitnessapp.utils.Initialization
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.realmListOf
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId

class RealmViewModel: ViewModel() {

    private val realm = Initialization.realm

    val categories = realm.query<Category>().find().toList()

    init {
        putDefaultData()
    }

    private fun putDefaultData() {
        viewModelScope.launch {
            realm.write {
                val firstUser = User().apply {
                    _id = ObjectId()
                    email = "firstUser@mail.ru"
                    password = "qwerty123"
                    userDetails = UserDetails().apply {
                        name = "Ivan"
                        gender = 1
                        weight = 70
                        height = 170
                        age = 20
                    }
                }
                val squats = Exercise().apply {
                    _id = ObjectId()
                    name = "Приседания"
                    avatarPath = "images/category_legs_squats.png"
                    exerciseDetails = ExerciseDetails().apply {
                        videoPath = "videos/category_legs_squats.mp4"
                        description = """
                            Классические приседания — это базовое упражнение для развития силы и выносливости мышц ног, ягодиц и корпуса.
                            Оно идеально подходит для укрепления нижней части тела и улучшения общей физической формы.

                            Техника выполнения:

                            1.Встаньте прямо, ноги на ширине плеч, стопы слегка развернуты в стороны.

                            2.Руки вытяните перед собой для баланса или держите на поясе.

                            3.На вдохе начните опускаться вниз, сгибая колени и отводя таз назад, как будто садитесь на невидимый стул. Спину держите прямой, грудь слегка подайте вперед.

                            4.Опускайтесь до тех пор, пока бедра не станут параллельны полу (или чуть ниже, если позволяет гибкость). Колени не должны выходить за носки.

                            5.На выдохе мощно оттолкнитесь пятками от пола и вернитесь в исходное положение.
                        """.trimIndent()
                        importantText = """
                            Следите за тем, чтобы колени не заваливались внутрь.

                            Держите корпус напряженным, чтобы избежать излишней нагрузки на поясницу.

                            Для усложнения упражнения используйте дополнительный вес (гантели, штангу или утяжелители).
                        """.trimIndent()
                    }
                }
                val category = Category().apply {
                    _id = ObjectId()
                    name = "Ноги"
                    avatarPath = "images/category_legs.png"
                    exercises = realmListOf(squats)
                }

                copyToRealm(firstUser, updatePolicy = UpdatePolicy.ALL)
                copyToRealm(category, updatePolicy = UpdatePolicy.ALL)

            }
        }
    }
}