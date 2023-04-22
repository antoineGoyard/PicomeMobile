package fr.picom.picomemobile.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import fr.picom.picomemobile.data.response.LoginResponse
import fr.picom.picomemobile.data.response.UserResponse
import fr.picom.picomemobile.models.User
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SessionManager {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    const val COOKIE_KEY = "COOKIE"
    const val USER_KEY = "USER_OBJECT"


    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences("SESSION_PREFS", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    fun updateUserFromLogin(existingUser: UserResponse){
       val user = User(
            id = existingUser.id,
           city = existingUser.city,
            lastName = existingUser.lastName,
            firstName = existingUser.firstName,
            email = existingUser.email,
            password = existingUser.password,
            phoneNumber = existingUser.phoneNumber,
            numSiret = existingUser.numSiret,
            companyName = existingUser.companyName,
            roadName = existingUser.roadName,
            postalCode = existingUser.postalCode,
            roles = existingUser.roles,
            adList = existingUser.adList,
            verified = existingUser.verified
        )
        updateSessionUser(user)
    }
    fun updateSessionUser(user: User) {
        val json = Gson().toJson(user)
        editor.putString(USER_KEY, json).apply()
    }

    fun saveSessionUser(user: User) {
        val json = Gson().toJson(user)
        editor.putString(USER_KEY, json).apply()
    }

    fun getSessionUser(): User? {
        val json = sharedPreferences.getString(USER_KEY, null)
        return Gson().fromJson(json, User::class.java)
    }



    fun saveSessionCookie(response: retrofit2.Response<LoginResponse>?) {
        val cookies = response?.headers()?.get("Set-Cookie")
        cookies?.let { editor.putString(COOKIE_KEY, it).apply() }
    }

    fun  getSavedCookie(): String? {
        return sharedPreferences.getString(COOKIE_KEY, null)
    }

    fun clearSession() {
        editor.remove(COOKIE_KEY).apply()
    }

    fun getSessionInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            val builder = request.newBuilder()
            getSavedCookie()?.let { builder.addHeader("Cookie", it) }
            chain.proceed(builder.build())
        }
    }

    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://example.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()
    }

    private fun getOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(SessionManager.getSessionInterceptor())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return okHttpClientBuilder.build()
    }

}