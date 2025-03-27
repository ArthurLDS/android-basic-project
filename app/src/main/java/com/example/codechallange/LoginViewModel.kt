package com.example.codechallange

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult : LiveData<Boolean> = _loginResult

    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> = _loading

    fun login(username: String, password: String) {

        _loading.postValue(true)

        viewModelScope.launch {
            try {
                delay(2000)
                callFakeApi(username, password)
                _loading.postValue(false)
            } catch (ex: Exception) {
                _loginResult.postValue(false)
            }
        }
    }

    private fun callFakeApi(username: String, password: String) {
        if (username == "admin" && password == "admin")
            _loginResult.postValue(true)
        else
            _loginResult.postValue(false)
    }

}