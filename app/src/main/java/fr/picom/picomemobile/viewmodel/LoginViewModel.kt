package fr.picom.picomemobile.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import fr.picom.picomemobile.data.request.LoginRequest
import fr.picom.picomemobile.data.request.RegisterRequest
import fr.picom.picomemobile.data.response.BaseResponse
import fr.picom.picomemobile.data.response.LoginResponse
import fr.picom.picomemobile.data.response.RegisterResponse
import fr.picom.picomemobile.repository.UserRepository
import fr.picom.picomemobile.utils.SessionManager.getSavedCookie
import fr.picom.picomemobile.utils.SessionManager.saveSessionCookie
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    val userRepo = UserRepository()
    val loginResult: MutableLiveData<BaseResponse<LoginResponse>> = MutableLiveData()
    val registerResult: MutableLiveData<BaseResponse<RegisterResponse>> = MutableLiveData()


    fun loginUser(email: String, pwd: String) {

        loginResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val loginRequest = LoginRequest(
                    password = pwd,
                    email = email
                )
                val response = userRepo.loginUser(loginRequest = loginRequest)

                if (response?.code() == 200) {
                    saveSessionCookie(response)
                    loginResult.value = BaseResponse.Success(response.body())
                } else {
                    loginResult.value = BaseResponse.Error(response?.message())
                }

            } catch (ex: Exception) {
                loginResult.value = BaseResponse.Error(ex.message)
            }
        }
    }

    fun registerUser(email: String, pwd: String) {

        registerResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val registerRequest = RegisterRequest(
                    password = pwd,
                    email = email
                )
                val response = userRepo.registerUser(registerRequest = registerRequest)
                if (response?.code() == 200) {
                    registerResult.value = BaseResponse.Success(response.body())
                } else {
                    registerResult.value = BaseResponse.Error(response?.message())
                }

            } catch (ex: Exception) {
                registerResult.value = BaseResponse.Error(ex.message)
            }
        }
    }
}