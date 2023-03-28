package fr.picom.picomemobile.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import fr.picom.picomemobile.data.request.LoginRequest
import fr.picom.picomemobile.data.response.BaseResponse
import fr.picom.picomemobile.data.response.LoginResponse
import fr.picom.picomemobile.data.response.UserResponse
import fr.picom.picomemobile.models.User
import fr.picom.picomemobile.repository.UserRepository
import fr.picom.picomemobile.utils.SessionManager
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application)  {
    val userResult: MutableLiveData<BaseResponse<UserResponse>> = MutableLiveData()
    val userRepo = UserRepository()

    fun getUser(id: Int, cookie: String) {


        viewModelScope.launch {
            try {

                val response = userRepo.getUser(id,cookie)

                if (response?.code() == 200) {
                    userResult.value = BaseResponse.Success(response.body())

                }
            } catch (ex: Exception) {
            }
        }
    }

}