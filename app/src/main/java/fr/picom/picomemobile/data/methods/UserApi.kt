package fr.picom.picomemobile.data.methods

import fr.picom.picomemobile.data.ApiClient
import fr.picom.picomemobile.data.request.LoginRequest
import fr.picom.picomemobile.data.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("/auth/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    companion object {
        fun getApi(): UserApi? {
            return ApiClient.client?.create(UserApi::class.java)
        }
    }
}