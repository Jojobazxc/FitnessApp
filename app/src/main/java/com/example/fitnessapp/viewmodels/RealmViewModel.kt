package com.example.fitnessapp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.models.Category
import com.example.fitnessapp.models.Exercise
import com.example.fitnessapp.models.ExerciseDetails
import com.example.fitnessapp.models.User
import com.example.fitnessapp.utils.Initialization
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.realmListOf
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId

class RealmViewModel : ViewModel() {

    private val realm = Initialization.realm

    init {
        Log.d("Realm", "Начальные данные успешно загружены")
        putDefaultData()
    }

    fun getCategories(): List<Category> {
        var categories: List<Category> = listOf()
        viewModelScope.launch {
            categories = realm.query<Category>().find().toList()
        }
        return categories
    }

    fun getListOfExercisesByName(name: String): List<Exercise> {
        var listOfExercises: List<Exercise> = listOf()
        viewModelScope.launch {
            listOfExercises =
                realm.query<Category>("name = $0", name).find().first().exercises?.toList()!!
        }
        return listOfExercises
    }

    fun getExerciseDetailByName(name: String): ExerciseDetails {
        var exerciseDetails = ExerciseDetails()
        viewModelScope.launch {
            exerciseDetails =
                realm.query<Exercise>("name = $0", name).find().first().exerciseDetails!!
        }
        return exerciseDetails
    }


    fun getUserById(objectId: ObjectId): User {
        val user = realm.query<User>("_id == $0", objectId).find().first()
        return user
    }

    fun deleteAllUsers() {
        realm.writeBlocking {
            delete(User::class)
        }
    }

    fun deleteAllCategories() {
        realm.writeBlocking {
            delete(Category::class)
        }
    }

    //Раскомментить функцию в случае, если нужно загрузить начальные данные
    private fun putDefaultData() {
        viewModelScope.launch {
            realm.write {
                val squats = Exercise().apply {
                    name = "Приседания"
                    avatarPath = "images/category_legs_squats.png"
                    exerciseDetails = ExerciseDetails().apply {
                        videoPath = "images/category_legs_squats.png"
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
                val categoryLegs = Category().apply {
                    name = "Ноги"
                    avatarPath = "images/category_legs.png"
                    exercises = realmListOf(squats)
                }
                val pullUps = Exercise().apply {
                    name = "Подтягивания"
                    avatarPath = "images/category_arms_pull_ups.png"
                    exerciseDetails = ExerciseDetails().apply {
                        videoPath = "images/category_arms_pull_ups.png"
                        description = """
                            Подтягивание — базовое упражнение для развития силы и выносливости мышц верхней части тела.
                            Оно задействует широчайшие мышцы спины, бицепсы, плечи, предплечья и мышцы кора, улучшает 
                            осанку и укрепляет хват. 
                             
                            Техника выполнения:
                            
                            Возьмитесь за перекладину хватом чуть шире плеч (прямой или обратный хват).
                            Повисните, полностью выпрямив руки. Ноги слегка согните или скрестите.
                             
                            Выполнение: 
                             
                            1. На выдохе подтянитесь, пока подбородок не окажется выше перекладины. 
                             
                            2. Движение должно быть плавным, без рывков. Акцент на мышцы спины. 
                             
                            3. В верхней точке задержитесь на 1-2 секунды. 
                             
                            Опускание: 
                            На вдохе медленно опуститесь, полностью выпрямляя руки. 
                        """.trimIndent()
                        importantText = """
                            1. Дыхание: Подъем на выдохе, опускание на вдохе. 
                             
                            2. Амплитуда: Полная амплитуда — залог эффективности. 
                             
                            3. Контроль: Избегайте раскачивания и рывков. 
                        """.trimIndent()
                    }
                }
                val pushUps = Exercise().apply {
                    name = "Отжимания от пола"
                    avatarPath = "images/category_arms_push_ups.png"
                    exerciseDetails = ExerciseDetails().apply {
                        videoPath = "images/category_arms_push_ups.png"
                        description = """
                            Отжимания — эффективное упражнение для развития силы и выносливости мышц верхней части тела.
                            Задействует грудные мышцы, трицепсы, плечи и корпус. Не требует оборудования и подходит для
                            любого уровня подготовки. 
 
                            Техника выполнения:
                            
                            1. Примите упор лежа, руки на ширине плеч, тело прямое от головы до пяток. 
                             
                            2. Напрягите мышцы кора. 
                              
                            3. На вдохе согните руки, опуская тело до уровня, когда грудь почти коснется пола. 
                             
                            На выдохе выпрямите руки, возвращаясь в исходное положение.
                        """.trimIndent()
                        importantText = """
                            1. Дыхание: Опускание на вдохе, подъем на выдохе. 
 
                            2. Амплитуда: Полная амплитуда — залог эффективности. 
                             
                            3. Контроль тела: Держите тело прямым, не прогибайтесь в пояснице. 
                        """.trimIndent()
                    }
                }
                val bar = Exercise().apply {
                    name = "Планка на время"
                    avatarPath = "images/category_arms_bar.png"
                    exerciseDetails = ExerciseDetails().apply {
                        videoPath = "images/category_arms_bar.png"
                        description = """
                            Планка — статическое упражнение для укрепления мышц кора (пресс, спина, плечи, ягодицы).
                            Улучшает осанку, выносливость и стабильность тела. Не требует оборудования. 
 
                            Техника выполнения: 

                            1. Упор лежа на предплечьях, локти под плечами. 
                             
                            2. Ноги вытянуты, тело прямое от головы до пяток. 
                             
                            Выполнение: 
                             
                            1. Напрягите мышцы кора и ягодиц. 
                             
                            2. Удерживайте положение, не допуская провисания бедер. 
                             
                            3. Голова в нейтральном положении, взгляд в пол. 
                             
                            Завершение: 
                             
                            Опуститесь на пол, сохраняя контроль. 
                        """.trimIndent()
                        importantText = """
                            1. Дыхание: Дышите ровно, не задерживайте дыхание. 
 
                            2. Контроль тела: Держите тело прямым, без прогибов.  
                        """.trimIndent()
                    }
                }

                val categoryArms = Category().apply {
                    name = "Руки"
                    avatarPath = "images/category_arms.png"
                    exercises = realmListOf(pullUps, pushUps, bar)
                }

                val press = Exercise().apply {
                    name = "Упражнение на пресс"
                    avatarPath = "images/category_stomach_press.png"
                    exerciseDetails = ExerciseDetails().apply {
                        videoPath = "images/category_stomach_press.png"
                        description = """
                            Упражнения на пресс направлены на укрепление мышц живота, включая прямую и косые мышцы.
                            Они помогают улучшить осанку, стабилизировать корпус и создать рельефный живот.
                            Упражнения на пресс могут выполняться в различных вариациях, что делает их доступными для любого уровня подготовки. 
 
                            Техника выполнения (пример: скручивания): 

                            1. Лягте на спину, согните ноги в коленях, стопы поставьте на пол. 
                             
                            2. Руки заведите за голову или скрестите на груди. 
                             
                            3. Поясница прижата к полу. 
                             
                            Выполнение: 
                             
                            1. На выдохе напрягите мышцы живота и поднимите верхнюю часть тела, отрывая лопатки от пола. 
                             
                            2. Не тяните голову руками, движение должно быть за счет мышц пресса. 
                             
                            3. В верхней точке задержитесь на 1-2 секунды. 
                             
                            Возврат в исходное положение: 
                             
                            1. На вдохе медленно опустите корпус обратно, не расслабляя мышцы полностью.   
                        """.trimIndent()
                        importantText = """
                            1. Дыхание: Подъем на выдохе, опускание на вдохе. 
 
                            2. Контроль тела: Не отрывайте поясницу от пола, движение должно быть плавным. 
                        """.trimIndent()
                    }
                }
                val legsUp = Exercise().apply {
                    name = "Подъем ног"
                    avatarPath = "images/category_stomach_legs_up.png"
                    exerciseDetails = ExerciseDetails().apply {
                        videoPath = "images/category_stomach_legs_up.png"
                        description = """
                            Подъем ног — это эффективное упражнение для укрепления мышц нижнего пресса, а также мышц кора и бедер.
                            Оно помогает улучшить стабильность корпуса, развить силу и выносливость. Упражнение может выполняться на
                            полу или на турнике, в зависимости от уровня подготовки. 
 
                            Техника выполнения (на полу): 

                            1. Лягте на спину, руки вытяните вдоль тела или положите под ягодицы для поддержки поясницы. 
                             
                            2. Ноги выпрямлены и соединены вместе. 
                             
                            Выполнение: 
                             
                            1. На выдохе напрягите мышцы пресса и поднимите ноги вверх до угла 90 градусов (или выше, если возможно). 
                             
                            2. Движение должно быть плавным, без рывков. 
                             
                            3. В верхней точке задержитесь на 1-2 секунды. 
                             
                            Возврат в исходное положение: 
                             
                            1. На вдохе медленно опустите ноги вниз, не касаясь пола, чтобы сохранить напряжение в мышцах. 
                        """.trimIndent()
                        importantText = """
                            1. Дыхание: Подъем ног на выдохе, опускание на вдохе. 
 
                            2. Контроль тела: Не отрывайте поясницу от пола, движение должно быть плавным и контролируемым. 
                        """.trimIndent()
                    }
                }
                val seatLegsUp = Exercise().apply {
                    name = "Подъем ног в положении сидя"
                    avatarPath = "images/category_stomach_seat_legs_up.png"
                    exerciseDetails = ExerciseDetails().apply {
                        videoPath = "images/category_stomach_seat_legs_up.png"
                        description = """
                            Подъем ног в положении сидя — это упражнение, направленное на укрепление мышц нижнего пресса,
                            а также на развитие стабильности корпуса и улучшение контроля над телом. Оно выполняется без
                            дополнительного оборудования и подходит для любого уровня подготовки. 
 
                            Техника выполнения: 
                            
                            1. Сядьте на пол, слегка отклоните корпус назад, удерживая баланс на ягодицах. 
                             
                            2. Руки поставьте на пол немного позади себя для поддержки или держите перед собой для усложнения. 
                             
                            3. Ноги выпрямлены и соединены вместе. 
                             
                            Выполнение: 
                             
                            1. На выдохе напрягите мышцы пресса и поднимите ноги вверх, сгибая их в коленях или держа прямыми (в зависимости от уровня подготовки). 
                             
                            2. Движение должно быть плавным, без рывков. 
                             
                            3. В верхней точке задержитесь на 1-2 секунды. 
                             
                            Возврат в исходное положение: 
                             
                            1. На вдохе медленно опустите ноги вниз, не касаясь пола, чтобы сохранить напряжение в мышцах.  
                        """.trimIndent()
                        importantText = """
                            1. Дыхание: Подъем ног на выдохе, опускание на вдохе. 
 
                            2. Контроль тела: Держите корпус стабильным, не сутультесь и не раскачивайтесь.   
                        """.trimIndent()
                    }
                }
                val categoryStomach = Category().apply {
                    name = "Живот"
                    avatarPath = "images/category_stomach.png"
                    exercises = realmListOf(legsUp, press, seatLegsUp)
                }

                copyToRealm(categoryLegs, updatePolicy = UpdatePolicy.ALL)
                copyToRealm(categoryArms, updatePolicy = UpdatePolicy.ALL)
                copyToRealm(categoryStomach, updatePolicy = UpdatePolicy.ALL)

                Log.d("Realm", "Функция с загрузкой успешно отработала")

            }
        }
    }
}