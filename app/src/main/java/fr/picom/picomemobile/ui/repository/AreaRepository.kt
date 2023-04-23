package fr.picom.picomemobile.ui.repository

import fr.picom.picomemobile.data.methods.UserApi
import fr.picom.picomemobile.data.response.AreaResponse
import fr.picom.picomemobile.models.Area
import retrofit2.Response

class AreaRepository {

         suspend fun getArea(cookie : String): List<Area>? {
            println(UserApi.getApi()?.getArea(cookie))
            return  UserApi.getApi()?.getArea(cookie)
        }

}