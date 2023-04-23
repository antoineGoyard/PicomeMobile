package fr.picom.picomemobile.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import fr.picom.picomemobile.data.request.LoginRequest
import fr.picom.picomemobile.data.request.UpdateRequest
import fr.picom.picomemobile.data.response.BaseResponse
import fr.picom.picomemobile.models.User
import fr.picom.picomemobile.ui.repository.UserRepository
import fr.picom.picomemobile.utils.SessionManager
import kotlinx.coroutines.launch


class ProfilViewModel(application: Application) : AndroidViewModel(application) {
    val userRepo = UserRepository()

    suspend fun updateUser(
        lastName: String,
        firstName: String,
        email: String,
        phone: String,
        siret: String,
        company: String,
        road: String,
        postal: String
    ) {
        viewModelScope.launch {
            try {
                val updateRequest = SessionManager.getSessionUser()?.let {
                    UpdateRequest(
                        id = it.id,
                        lastName = lastName,
                        firstName = firstName,
                        email = email,
                        password = it.password,
                        phoneNumber = phone,
                        numSiret = siret,
                        companyName = company,
                        roadName = road,
                        postalCode = postal,
                        roles = it.roles,
                        adList = it.adList,
                        verified = it.verified
                    )
                }
                    userRepo.updateUser(SessionManager.getSessionUser()?.id!!, updateRequest!!, SessionManager.getSavedCookie()!!)

            } catch (ex: Exception) {

            }
        }
    }
}


