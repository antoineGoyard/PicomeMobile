package fr.picom.picomemobile.repository

import fr.picom.picomemobile.data.methods.UserApi
import fr.picom.picomemobile.data.request.LoginRequest
import fr.picom.picomemobile.data.response.LoginResponse
import retrofit2.Response

class UserRepository {

    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse>? {
        return  UserApi.getApi()?.loginUser(loginRequest = loginRequest)
    }
}