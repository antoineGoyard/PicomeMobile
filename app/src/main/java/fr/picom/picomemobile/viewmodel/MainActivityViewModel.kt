package fr.picom.picomemobile.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import fr.picom.picomemobile.data.response.AreaResponse
import fr.picom.picomemobile.data.response.BaseResponse
import fr.picom.picomemobile.data.response.TimeIntervalResponse
import fr.picom.picomemobile.data.response.UserResponse
import fr.picom.picomemobile.ui.repository.AreaRepository
import fr.picom.picomemobile.ui.repository.TimeIntervalRepository
import fr.picom.picomemobile.ui.repository.UserRepository
import fr.picom.picomemobile.utils.SessionManager.saveSessionArea
import fr.picom.picomemobile.utils.SessionManager.saveSessionTimeInterval
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application)  {
    val userResult: MutableLiveData<BaseResponse<UserResponse>> = MutableLiveData()
    val timeIntervalResult: MutableLiveData<BaseResponse<TimeIntervalResponse>> = MutableLiveData()
    val areaResult: MutableLiveData<BaseResponse<AreaResponse>> = MutableLiveData()
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