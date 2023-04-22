package fr.picom.picomemobile.data.methods

import fr.picom.picomemobile.data.ApiClient
import fr.picom.picomemobile.data.request.LoginRequest
import fr.picom.picomemobile.data.request.RegisterRequest
import fr.picom.picomemobile.data.request.UpdateRequest
import fr.picom.picomemobile.data.response.LoginResponse
import fr.picom.picomemobile.data.response.RegisterResponse
import fr.picom.picomemobile.data.response.UserResponse
import fr.picom.picomemobile.models.User
import retrofit2.Response
import retrofit2.http.*

interface UserApi {

    @POST("/auth/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("/auth/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    @PATCH("/api/user/{id}")
    suspend fun updateUser( @Path("id") id: Int, @Body UpdateRequest: UpdateRequest,  @Header("Cookie") cookie: String): Response<UserResponse>

        @GET("/api/user/{id}")
        suspend fun getUser(
            @Path("id") id: Int,
            @Header("Cookie") cookie: String
        ): Response<UserResponse>

    @GET("/api/area")
    suspend fun getArea(): Response<RegisterResponse>

    companion object {
        fun getApi(): UserApi? {
            return ApiClient.client?.create(UserApi::class.java)
        }
    }
}