package com.mintic22g1.recetapp.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mintic22g1.recetapp.data.models.UserModel
import com.mintic22g1.recetapp.data.repositories.LoginRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel(private val repo: LoginRepository): ViewModel() {
    private var _login: MutableLiveData<Boolean> = MutableLiveData()
    val login: LiveData<Boolean> get() = _login

    private var _signUp: MutableLiveData<Boolean> = MutableLiveData()
    val signUp: LiveData<Boolean> get() = _signUp

    private var _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> get() = _error

    private var _user: MutableLiveData<UserModel> = MutableLiveData()
    val user: LiveData<UserModel> get() = _user

    fun login(email: String, password: String){
        viewModelScope.launch {
            try {
                repo.login(email, password)
                _login.postValue(true)
            } catch (e : Exception) {
                _error.postValue(e.message)

            }
        }
    }

    fun signUp(email: String, password: String, name: String, gender: String){
        viewModelScope.launch {
            try {
                repo.signUp(email, password, name, gender)
                _signUp.postValue(true)
            } catch (e : Exception) {
                _error.postValue(e.message)
            }
        }
    }

    fun currentUser() {
        viewModelScope.launch {
            _user.postValue(repo.currentUser())
        }
    }

}