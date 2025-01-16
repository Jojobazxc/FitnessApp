package com.example.fitnessapp.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.models.User
import com.example.fitnessapp.models.UserDetails
import com.example.fitnessapp.utils.AuthState
import com.example.fitnessapp.utils.Initialization
import com.example.fitnessapp.utils.SharedPreferencesManager
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId


class AuthViewModel(private val context: Context) : ViewModel() {

    private val realm = Initialization.realm

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    fun register(
        email: String,
        password: String,
        name: String,
        gender: Int,
        height: Int,
        weight: Int,
        age: Int
    ) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val existingUser = realm.query<User>("email == $0", email).first().find()
                if (existingUser != null) {
                    _authState.value = AuthState.Error("Пользователь с таким email уже существует")

                    return@launch
                }
                realm.write {
                    val newUser = User().apply {
                        _id = ObjectId()
                        this.email = email
                        this.password = password
                        this.userDetails = UserDetails().apply {
                            this.name = name
                            this.age = age
                            this.height = height
                            this.weight = weight
                            this.gender = gender
                        }
                    }
                    copyToRealm(newUser)
                }
                _authState.value = AuthState.Success("Регистрация прошла успешно")
            } catch (e: Exception) {
                _authState.value = AuthState.Error("Ошибка при регистрации: ${e.message}")
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val user =
                    realm.query<User>("email == $0 AND password == $1", email, password).first()
                        .find()

                if (user != null) {
                    saveAuthForCurrentUser(user = user)
                    _authState.value = AuthState.Success("Авторизация успешна")
                } else {
                    _authState.value = AuthState.Error("Ошибка авторизации")
                }
            } catch (e: Exception) {
                _authState.value = AuthState.Error("Ошибка при выполнении авторизации")
            }
        }
    }

    private fun saveAuthForCurrentUser(user: User) {
        SharedPreferencesManager.init(context = context)
        viewModelScope.launch {
            realm.write {
                val loggedUser = User().apply {
                    _id = user._id
                    email = user.email
                    password = user.password
                    userDetails = user.userDetails
                    isLogged = true
                }
                SharedPreferencesManager.putObjectId(user._id)
                copyToRealm(loggedUser, UpdatePolicy.ALL)
            }
        }
    }

    private fun getCurrentUserObjectId(email: String): ObjectId? {
        return realm.query<User>("email == $0", email).first().find()?._id
    }

    fun isUserLoggedIn(): Boolean? {
        SharedPreferencesManager.init(context = context)
        val objectId = SharedPreferencesManager.getObjectId()
        val user = realm.query<User>("_id == $0", objectId).first().find()

        return user?.isLogged
    }

    fun logout(email: String) {
        val userId = getCurrentUserObjectId(email)
        viewModelScope.launch {
            realm.write {
                val currentUser = realm.query<User>("_id == $0", userId).first().find()
                currentUser?.isLogged = false
            }
        }
    }

}