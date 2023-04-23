package fr.picom.picomemobile.ui.repository

import fr.picom.picomemobile.data.methods.UserApi
import fr.picom.picomemobile.data.request.LoginRequest
import fr.picom.picomemobile.data.request.RegisterRequest
import fr.picom.picomemobile.data.request.UpdateRequest
import fr.picom.picomemobile.data.request.UserRequest
import fr.picom.picomemobile.data.response.LoginResponse
import fr.picom.picomemobile.data.response.RegisterResponse
import fr.picom.picomemobile.data.response.UserResponse
import retrofit2.Response

class UserRepository {

    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse>? {
        return  UserApi.getApi()?.loginUser(loginRequest = loginRequest)
    }

    suspend fun registerUser(registerRequest: RegisterRequest): Response<RegisterResponse>? {
        return  UserApi.getApi()?.registerUser(registerRequest = registerRequest)
    }

    suspend fun getUser(id : Int, cookie : String): Response<UserResponse>? {
        return  UserApi.getApi()?.getUser(id,cookie)
    }

    suspend fun updateUser(id :Int,updateRequest: UpdateRequest,cookie : String): Response<UserResponse>? {
        return  UserApi.getApi()?.updateUser(id,updateRequest,cookie)
    }


}