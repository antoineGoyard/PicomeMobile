package fr.picom.picomemobile.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import fr.picom.picomemobile.data.response.AreaResponse
import fr.picom.picomemobile.data.response.BaseResponse
import fr.picom.picomemobile.ui.repository.AreaRepository
import fr.picom.picomemobile.ui.repository.TimeIntervalRepository
import fr.picom.picomemobile.ui.repository.UserRepository
import fr.picom.picomemobile.utils.SessionManager
import kotlinx.coroutines.launch


class AdViewModel(application: Application) : AndroidViewModel(application) {
    val areaRepo = AreaRepository()
    val areaResult: MutableLiveData<BaseResponse<AreaResponse>> = MutableLiveData()


    fun getArea(cookie: String) {
        viewModelScope.launch {
            try {
                val area = AreaRepository().getArea(cookie)

                if (area != null) {
                    SessionManager.saveSessionArea(area)
                }

               /* if (area?.code() == 200) {
                    areaResult.value = BaseResponse.Success(area.body())
                }*/
            } catch (ex: Exception) {
            }


        }
    }

    fun getTimeInterval(cookie: String) {
        viewModelScope.launch {
            try {
                val timeInterval = TimeIntervalRepository().getTimeInterval(cookie)

                if (timeInterval!= null) {
                    SessionManager.saveSessionTimeInterval(timeInterval)
                }

                /* if (area?.code() == 200) {
                     areaResult.value = BaseResponse.Success(area.body())
                 }*/
            } catch (ex: Exception) {
            }


        }
    }

}