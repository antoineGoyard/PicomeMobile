package fr.picom.picomemobile.ui.repository

import fr.picom.picomemobile.data.methods.UserApi

import fr.picom.picomemobile.data.response.TimeIntervalResponse
import fr.picom.picomemobile.models.TimeInterval
import retrofit2.Response

class TimeIntervalRepository {
    suspend fun getTimeInterval(cookie : String): List<TimeInterval>? {
        return  UserApi.getApi()?.getTimeInterval(cookie)
    }
}